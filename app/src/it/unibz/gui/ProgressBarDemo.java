

package it.unibz.gui;

import it.unibz.algorithms.DBScan;
import it.unibz.algorithms.KMeans;
import it.unibz.algorithms.Utility;
import it.unibz.algorithms.types.DataPoint;

import java.awt.*;
import javax.swing.*;

import java.beans.*;
import java.util.List;
import java.util.Vector;

public class ProgressBarDemo extends JPanel implements PropertyChangeListener 
                             {

    public JProgressBar progressBar;
    private JTextArea taskOutput;
    private SwingWorker<Void,Void> task;
    private boolean iskmeans=true;
    private JTextArea dataoutput;
    
    
    public void StartupDBScan(List<DataPoint> dataPoints, int e,int minp){
      task = new DBScan(dataPoints,e,minp);
      task.addPropertyChangeListener(this);
      task.execute();
    }

    public ProgressBarDemo(boolean iskmeans,JTextArea result) {
        super(new BorderLayout());
this.dataoutput=result;
       this.iskmeans=iskmeans;
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        taskOutput = new JTextArea(5, 20);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    }

    /**
     * Invoked when task's progress property changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            taskOutput.setText(String.format(
                    "Completed %d%% of "+(iskmeans?"KMeans":"DBScan"), task.getProgress()));
            if(progress==100){
            	if(!iskmeans)
            		dataoutput.setText(Utility.display(DBScan.getScanResult()));
            	else
            		dataoutput.setText(Utility.display(((KMeans)task).getClusterOutput()));
            	((JDialog)ProgressBarDemo.this.getParent().getParent().getParent()).dispose();
            }
        } 
    }

		public void StartupKMeans(Vector dataPoints, int parseInt, int parseInt2) {
      task = new KMeans(parseInt,parseInt2,dataPoints);
      task.addPropertyChangeListener(this);
      task.execute();			
		}

}
