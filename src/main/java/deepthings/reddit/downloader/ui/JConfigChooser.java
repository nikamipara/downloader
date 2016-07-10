package deepthings.reddit.downloader.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import deepthings.reddit.downloader.callBacks.ConfingChooserCallBack;
import deepthings.reddit.downloader.constants.AppWideConstants;
import deepthings.reddit.downloader.model.DConfig;
import deepthings.reddit.downloader.utils.StringUtils;

public class JConfigChooser extends JFrame {

	private ConfingChooserCallBack callBack;
	private JavaFileChooser filePicker;
	private JTextField subR, Limit;
	private JComboBox<String> cat;

	private static String[] category = { "hot", "top", "rising", "new",
			"controversial", "gilded" };

	public JConfigChooser(ConfingChooserCallBack cb) {
		super("SubReddit Media Downloader");
		callBack = cb;

		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(520, 170);
		setLocationRelativeTo(null); // center on screen

		// set up a file picker component
		filePicker = new JavaFileChooser();
		filePicker.setMode(JavaFileChooser.MODE_SAVE);
		// access JFileChooser class directly
		JFileChooser fileChooser = filePicker.getFileChooser();
		fileChooser.setCurrentDirectory(new File("C:/"));
		add(filePicker);

		// Set up the content pane.
		Container contentPane = new Container();
		FlowLayout layout = new FlowLayout();
		contentPane.setLayout(layout);

		// Create and add the components.
		JLabel label = new JLabel("Subreddit: ");
		subR = new JTextField("pics", 15);
		contentPane.add(label);
		contentPane.add(subR);
		add(contentPane);

		// setup limit of posts
		label = new JLabel("Limit: ");
		Limit = new JTextField("100", 5);
		contentPane.add(label);
		contentPane.add(Limit);
		add(contentPane);

		// setup choose
		contentPane = new Container();
		layout = new FlowLayout();
		contentPane.setLayout(layout);
		label = new JLabel("Category");
		contentPane.add(label);
		cat = new JComboBox<String>();
		for (int i = 0; i < 4; i++)
			cat.addItem(category[i]);
		contentPane.add(cat);
		add(contentPane);

		// add download button!
		JButton go = new JButton("Start Dowlnoad!");
		go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				startDownload(evt);
			}

		});

		add(go);

	}

	private void startDownload(ActionEvent evt) {
		// parse all the things here.
		String downloadPath = filePicker.getSelectedFilePath();
		String subReddit = subR.getText();
		String category = (String) cat.getSelectedItem();
		int limit;
		try {
			limit = Integer.parseInt(Limit.getText());
		} catch (NumberFormatException e) {
			limit = AppWideConstants.DOWNLOAD_POST_LIMIT; // default limit.
		}

		if (StringUtils.isEmpty(downloadPath) || StringUtils.isEmpty(subReddit))
			return;
		else {
			DConfig config = new DConfig();
			config.subReddit = subReddit;
			config.downloadPath = downloadPath;
			config.limit = limit;
			if (!StringUtils.isEmpty(category))
				config.category = category;
			callBack.call(config);
			this.dispose();
		}
	}

}
