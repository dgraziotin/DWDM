package it.unibz.algorithms.types;
/**
 * Copyright (C) 1999  Walter Bogaardt
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 *
 *  Project:   Home Automation Interface
 *  Filename:  $Id: OpenFileDialog.java,v 1.0 1999/09/14 23:42:04 wbogaardt
 */
import javax.swing.JFileChooser;
import java.io.File;

/**
 * This is a simple file open dialog, which uses a filter to only
 * show .x10 file extensions.
 */
public class OpenFileDialog {
    private static JFileChooser jfcChooser = null;
    private String sDirectory;
    private boolean bIsFile = false;

    /**
     * Constructor to create and show a open file dialog
     * with only .x10 files showing as default.
     */
    public OpenFileDialog() {
        if (jfcChooser == null)
        {
            jfcChooser = new JFileChooser();
        }
        int fileState = jfcChooser.showOpenDialog(null);
        File file = jfcChooser.getSelectedFile();
        if (file != null && fileState == JFileChooser.APPROVE_OPTION)
        {
            setFilePath(file.getAbsolutePath());
        }
        else if (fileState == JFileChooser.CANCEL_OPTION)
        {
            bIsFile = false;
        }
    }

    /**
     * Gets the file path directory from the open file dialog
     * @return String of the filepath.
     */
    public String getFilePath() {
        return sDirectory;
    }

    /**
     * If a file was selected this returns a true.
     * @return true file was selected else a false
     */
    public boolean isFileSelected() {
        return bIsFile;
    }

    /**
     * Allows setting of the file path
     * @param fpath String of the file path
     */
    private void setFilePath(String fpath) {
        sDirectory = fpath;
        bIsFile = true;
    }
}





