package deepthings.reddit.downloader.ui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import deepthings.reddit.downloader.callBacks.ProgressCallback;

public class JProgress extends JPanel {
	JLabel status;
	private JTextArea taskOutput;
	private JProgressBar progressBar;
	private int limit;
	private ProgressCallback cb;
	private JButton cancelB;

	public JProgress(int limit, ProgressCallback c) {

		super(new BorderLayout());
		cb = c;
		this.limit = limit;
		// Create the demo's UI.

		progressBar = new JProgressBar(0, limit);
		progressBar.setValue(0);

		// Call setStringPainted now so that the progress bar height
		// stays the same whether or not the string is shown.
		progressBar.setStringPainted(true);

		taskOutput = new JTextArea(5, 20);
		taskOutput.setMargin(new Insets(5, 5, 5, 5));
		taskOutput.setEditable(false);
		status = new JLabel("Downloading...");
		JPanel panel = new JPanel();
		panel.add(status);
		panel.add(progressBar);
		add(panel, BorderLayout.PAGE_START);
		
		add(new JScrollPane(taskOutput), BorderLayout.CENTER);
		
		cancelB = new JButton("Cancel");
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				cb.cancelDownload();
			}

		});		add(cancelB,BorderLayout.PAGE_END);
		
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		// Create and add the components.
		/*
		 * JLabel label = new JLabel("Status: "); status = new
		 * JLabel("Processing...", 50); contentPane.add(label);
		 * contentPane.add(status); add(contentPane);
		 */
	}

	public void postProgress(String msg, int fails, int success) {
		progressBar.setIndeterminate(false);
		progressBar.setValue(fails + success);
		taskOutput.append(String.format("%s\n", msg));
		status.setText(String.format("Success:%d \nFail:%d", success, fails));

		if (fails + success > limit) {
			taskOutput.append(String.format("%s\n", "DOWNLOAD COMPLETE!!!!"));
			cancelB.setText("Close");
		}
	}
}
