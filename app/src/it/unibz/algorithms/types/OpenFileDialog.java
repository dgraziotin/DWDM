package it.unibz.algorithms.types;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class allows to filter which file can be selected or not by its extension in a open dialog.
 * We want that only arff files can be selected.
 */
public class OpenFileDialog {
	private static JFileChooser jfc = null;
	private String path;
	private boolean isAfile = false;

	/**
	 * This constructor creates an open file dialog
	 * with only arff files selectable.
	 */
	public OpenFileDialog() {
		if (jfc == null) {
			jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("ARFF Files", "arff");
			jfc.setFileFilter(filter);
			jfc.setAcceptAllFileFilterUsed(false);
		}

		int fileState = jfc.showOpenDialog(null);
		File file = jfc.getSelectedFile();
		if (file != null && fileState == JFileChooser.APPROVE_OPTION) {
			setFilePath(file.getAbsolutePath());
		}
		else if (fileState == JFileChooser.CANCEL_OPTION){
			isAfile = false;
		}
	}

	/**
	 * Gets the file path directory where the arff is placed
	 * @return String of the file path directory.
	 */
	public String getFilePath() {
		return path;
	}

	/**
	 * This method tells whether a file has been selected or not
	 * @return true if a file has been selected
	 */
	public boolean isFileSelected() {
		return isAfile;
	}

	/**
	 * Setter for the file path
	 * @param p String containing the file path
	 */
	private void setFilePath(String p) {
		path = p;
		isAfile = true;
	}
}





