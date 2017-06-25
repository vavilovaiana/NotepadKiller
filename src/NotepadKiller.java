import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class NotepadKiller
{
    public static void main(String[] args)
    {
        new NotepadGui();
    }

    private static class NotepadGui extends JFrame {
        private JTextArea jTextArea = new JTextArea();
        private JScrollPane jScroll = new JScrollPane(jTextArea);
        private JMenuBar jMenuBar = new JMenuBar();
        private File file = null;
        private String str = "";

        NotepadGui()
        {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JFrame frame = new JFrame("Frame");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Notepad_Iana");

            jMenuBar.add(createFileMenu());
            add(jScroll);
            setJMenuBar(jMenuBar);

            setSize(500, 700);
            setVisible(true);

        }

        private JMenu createFileMenu()
        {
            final JMenu menu = new JMenu("Меню");
            final JMenuItem open = new JMenuItem("Открыть");
            final JMenuItem save = new JMenuItem("Сохранить");
            final JMenuItem exit = new JMenuItem(new ExitAction());

            menu.add(open);
            menu.add(save);
            menu.addSeparator();
            menu.add(exit);

            open.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser fileopen = new JFileChooser();
                    int ret = fileopen.showDialog(null, "Открыть txt файл ");
                    if (ret == JFileChooser.APPROVE_OPTION)
                    {
                        file = fileopen.getSelectedFile();
                        str = file.getAbsolutePath();
                        readFile(str);
                    }
                }
            });

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveFile(str);
                }
            });
            return menu;
        }



        class ExitAction extends AbstractAction
        {
            ExitAction() {
                putValue(NAME, "Выход");
            }
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }

        private void readFile(String selectedFileName)
        {
            try
            {
                FileReader fr = new FileReader(selectedFileName);
                StringBuffer sb = new StringBuffer();
                int symbol;
                while((symbol = fr.read()) != -1)
                {
                    sb.append((char)symbol);
                }
                jTextArea.setText(sb.toString());
                fr.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        private void saveFile(String selectedFileName)
        {   try {
            FileWriter f = new FileWriter(selectedFileName);
            f.write(jTextArea.getText());
            f.flush();
            f.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        }
    }
}
