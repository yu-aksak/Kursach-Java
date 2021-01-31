package Client.Windows;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

public class DiagramWindow extends JFrame {
    private static JLabel data;

    public DiagramWindow(JTable table){
        try {
            TableModel model = table.getModel();

            DefaultPieDataset pieDataset = new DefaultPieDataset();
            int k = model.getRowCount();
            if (k > 5)
                k = 5;
            for (int i = 0; i < k; i++) {
                String name = (String) model.getValueAt(i, 2);
                int dd2 = Integer.parseInt((String) model.getValueAt(i, 3));
                pieDataset.setValue(name, dd2);
            }
            JFreeChart chart = ChartFactory.createPieChart("Диаграмма выдач", pieDataset, true, true, true);

            ChartFrame frame = new ChartFrame("Выдачи", chart);
            frame.setVisible(true);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            int width = 640;
            int height = 480;
            File pieChart = new File( "AnalData.png" );
            ChartUtilities.saveChartAsPNG( pieChart , chart , width , height );
        }
        catch (IOException ex) {
            System.out.println(ex);
        }

    }


}
