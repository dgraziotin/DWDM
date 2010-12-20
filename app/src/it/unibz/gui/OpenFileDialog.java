package it.unibz.gui;

import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

/**
 * A GUI Dialog to let user choose a file, that lets us filter only the
 * extensions we decide (in our case, .arff files)
 */
public class OpenFileDialog {
	/**
	 * Swing default File Chooser dialog
	 */
	private static JFileChooser jFileChooser = null;
	/**
	 * Contain the full path of the file chosen
	 */
	private String filePath;
	/**
	 * State variable, true when a file is selected
	 */
	private boolean isFileSelected = false;

	/**
	 * The constructor automatically looks if there is an instance of jFileChooser,
	 * or creates a new one if needed, it forces a filtering of .arff files,
	 * makes the dialog appear and sets the behavior of the OK and CANCEL buttons 
	 */
	public OpenFileDialog() {
		if (jFileChooser == null) {
			jFileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"ARFF Files", "arff");
			jFileChooser.setFileFilter(filter);
			jFileChooser.setAcceptAllFileFilterUsed(false);

		}
		int fileState = jFileChooser.showOpenDialog(null);
		File file = jFileChooser.getSelectedFile();
		if (file != null && fileState == JFileChooser.APPROVE_OPTION) {
			setFilePath(file.getAbsolutePath());
		} else if (fileState == JFileChooser.CANCEL_OPTION) {
			isFileSelected = false;
		}
	}

	/**
	 * Gets the file path directory from the open file dialog
	 * 
	 * @return String of the filepath.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * If a file was selected this returns a true.
	 * 
	 * @return true file was selected else a false
	 */
	public boolean isFileSelected() {
		return isFileSelected;
	}

	/**
	 * Allows setting of the file path
	 * 
	 * @param fpath
	 *            String of the file path
	 */
	private void setFilePath(String fpath) {
		filePath = fpath;
		isFileSelected = true;
	}
}
