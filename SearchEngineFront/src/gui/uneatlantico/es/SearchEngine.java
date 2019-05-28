package gui.uneatlantico.es;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import entities.uneatlantico.Document;
import entities.uneatlantico.WordLibrary;

import service.uneatlantico.InjectionModule;
import service.uneatlantico.Service;

import com.google.inject.Guice;
import com.google.inject.Injector;

@SuppressWarnings("serial")
public class SearchEngine extends JFrame {

	private JFileChooser chooseDirectory;
	private JLabel directoryLabel;
	private JTextField fullPath;
	private JButton indexButton;
	private JPanel indexTab;
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JProgressBar progressBar;
	private JLabel readyLabel;
	private JButton searchButton;
	private JLabel searchLabel;
	private JPanel searchTab;
	private JButton selectDirectory;
	private JTabbedPane tabs;
	private JTextField wordToSearch;
	private JButton eraseLibrary;
	
	private Service service; 

	private static Logger log = Logger.getLogger(SearchEngine.class.getName());

	public static void main(String args[]) {
		setUIManager();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				log.info("Running program...");
				new SearchEngine().setVisible(true);
			}
		});
	}

	public SearchEngine() {
		initComponents();
		setLocationRelativeTo(null);

		Injector injector = Guice.createInjector(new InjectionModule());
		this.service = injector.getInstance(Service.class);

		List<WordLibrary> wl = service.getSerializer().deserialize();
		if (wl != null) {
			service.getIndexer().setWordLibrary(wl);
		}
	}

	/**
	 * Accion del botón que muestra el seleccionador de archivos de Java y lo
	 * escribe en el TextField.
	 */
	private void selectDirectoryActionPerformed(ActionEvent evt) {
		log.info("Selecting directory");
		int returnVal = chooseDirectory.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			fullPath.setText(chooseDirectory.getSelectedFile().getAbsolutePath());
	}

	/**
	 * Acción que ejecuta el botón de indexar, llama a la funcion Index de la clase
	 * Service.
	 */
	private void indexButtonActionPerformed(ActionEvent evt) {
		log.info("Starting to index documents:" + wordToSearch.getText());
		class IndexWorker extends SwingWorker<String, Object> {
			protected String doInBackground() {
				readyLabel.setVisible(false);
				progressBar.setVisible(true);
				progressBar.setIndeterminate(true);
				if (!fullPath.getText().equals(""))
					if (service.getIndexer().getWordLibrary() == null)
						service.Index(fullPath.getText());
					else {
						service.Index(fullPath.getText());
					}
				return "Done.";
			}

			protected void done() {
				progressBar.setVisible(false);
				readyLabel.setVisible(true);
			}
		}
		new IndexWorker().execute();
	}

	/**
	 * Acción que ejecuta el botón de buscar, llama a la función Search de la clase
	 * Service.
	 */
	private void searchButtonActionPerformed(ActionEvent evt) {
		log.info("Searching word:" + wordToSearch.getText());
		List<Document> documents = this.service.Search(wordToSearch.getText());
		jPanel2.removeAll();
		repaint();
		if (documents != null)
			printDocuments(documents);
		else
			printNotFound(wordToSearch.getText());
	}

	/**
	 * Muestra en pantalla un mensaje que no se ha encontrado la palabra.
	 * 
	 * @param word
	 *            Palabra a mostrar, introducida por el usuario.
	 */
	public void printNotFound(String word) {
		JLabel label = new JLabel();
		label.setBackground(new Color(255, 255, 255));
		label.setFont(new Font("Century Gothic", 0, 18));
		label.setText("<html>Word <u><i>" + word + "</i></u> was not found.<br/>Try another one.</html>");
		label.setSize(690, 52);
		label.setLocation(new Point(5, 5));
		label.setVisible(true);

		jPanel2.add(label);
		repaint();
	}

	/**
	 * Imprime los documentos en el JPanel2
	 * 
	 * @param documents
	 *            Lista de objetos de tipo documentos.
	 */
	public void printDocuments(List<Document> documents) {
		int x = 5;
		int y = 5;

		for (int i = 0; i < documents.size(); i++) {
			JLabel label = new JLabel();
			label.setBackground(new Color(255, 255, 255));
			label.setFont(new Font("Century Gothic", 0, 12)); // NOI18N
			label.setText("<html><b>File Name: </b>" + documents.get(i).getName() + "<br/><b>Path: </b>"
					+ documents.get(i).getPath().substring(0, 75) + "...</html>");
			label.setSize(575, 52);
			label.setLocation(new Point(x, y));
			label.setVisible(true);

			x += 575;

			JButton button = new JButton();
			button.setBackground(new Color(255, 255, 255));
			button.setFont(new Font("Century Gothic", 0, 12)); // NOI18N
			button.setText("Open Document in:" + documents.get(i).getPath());
			button.setBorder(new LineBorder(new Color(153, 0, 0), 1, true));
			button.setContentAreaFilled(false);
			button.setSize(110, 46);
			button.setLocation(new Point(x, y));
			button.setVisible(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					openDocument(evt.getSource().toString().split("in:")[1].split(",")[0]);
				}
			});

			x = 5;
			y = 57 * (i + 1);
			jPanel2.add(label);
			jPanel2.add(button);
			jPanel2.setSize(740, y + 10);
			jPanel2.setPreferredSize(new Dimension(740, y + 10));
			jPanel2.revalidate();
			jPanel2.repaint();
			jScrollPane1.revalidate();
			jScrollPane1.repaint();
		}
		repaint();
	}

	/**
	 * Abre el documento en el ordenador.
	 * 
	 * @param path
	 *            Ruta del documento.
	 */
	private void openDocument(String path) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(new File(path));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Borra la librería global guardada.
	 * 
	 * @param evt
	 */
	private void eraseLibrary(ActionEvent evt) {
		try {
			File file = new File("C:\\logs\\wordLibrary.json");
			if (file.delete())
				service.getIndexer().setWordLibrary(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setUIManager() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			log.error("Class was not found loading Windows UIManager");
		} catch (InstantiationException ex) {
			log.error("Instantiaton Error in Windows UIManager");
		} catch (IllegalAccessException ex) {
			log.error("Illegal access loading Windows UIManager");
		} catch (UnsupportedLookAndFeelException ex) {
			log.error("UIManager is not supported");
		}
	}

	private void initComponents() {
		chooseDirectory = new JFileChooser();
		tabs = new JTabbedPane();
		indexTab = new JPanel();
		fullPath = new JTextField();
		indexButton = new JButton();
		directoryLabel = new JLabel();
		selectDirectory = new JButton();
		progressBar = new JProgressBar();
		readyLabel = new JLabel();
		searchTab = new JPanel();
		wordToSearch = new JTextField();
		searchLabel = new JLabel();
		searchButton = new JButton();
		jScrollPane1 = new JScrollPane();
		jPanel2 = new JPanel();
		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		eraseLibrary = new JButton();

		chooseDirectory.setApproveButtonText("Select");
		chooseDirectory.setCurrentDirectory(
				new File("C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II"));
		chooseDirectory.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooseDirectory.setPreferredSize(new Dimension(800, 500));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBackground(new Color(255, 255, 255));

		tabs.setBackground(new Color(255, 255, 255));
		tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.setFont(new Font("Century Gothic", 0, 16));

		indexTab.setBackground(new Color(255, 255, 255));

		fullPath.setFont(new Font("Century Gothic", 0, 14));

		indexButton.setBackground(new Color(255, 255, 255));
		indexButton.setFont(new Font("Century Gothic", 0, 20));
		indexButton.setText("Index");
		indexButton.setActionCommand("index");
		indexButton.setBorder(new LineBorder(new Color(153, 0, 0), 1, true));
		indexButton.setContentAreaFilled(false);
		indexButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		indexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				indexButtonActionPerformed(evt);
			}
		});

		directoryLabel.setFont(new Font("Century Gothic", 0, 18));
		directoryLabel.setText("Path:");

		selectDirectory.setBackground(new Color(255, 255, 255));
		selectDirectory.setIcon(new ImageIcon("C:\\Users\\David23\\Downloads\\folder.png"));
		selectDirectory.setBorder(BorderFactory.createLineBorder(new Color(153, 0, 0)));
		selectDirectory.setContentAreaFilled(false);
		selectDirectory.setCursor(new Cursor(Cursor.HAND_CURSOR));
		selectDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				selectDirectoryActionPerformed(evt);
			}
		});

		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setForeground(new Color(0, 153, 51));
		progressBar.setIndeterminate(true);
		progressBar.setVisible(false);

		readyLabel.setFont(new Font("Century Gothic", 0, 18)); // NOI18N
		readyLabel.setText("Ready!");
		readyLabel.setVisible(false);

		eraseLibrary.setText("Erase Library");
		eraseLibrary.setCursor(new Cursor(Cursor.HAND_CURSOR));
		eraseLibrary.setFont(new Font("Century Gothic", 0, 14)); // NOI18N
		eraseLibrary.setBorder(new LineBorder(new Color(153, 0, 0), 1, true));
		eraseLibrary.setContentAreaFilled(false);
		eraseLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				eraseLibrary(evt);
			}
		});

		GroupLayout indexTabLayout = new GroupLayout(indexTab);
		indexTab.setLayout(indexTabLayout);
		indexTabLayout.setHorizontalGroup(indexTabLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(indexTabLayout.createSequentialGroup().addGroup(indexTabLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(indexTabLayout.createSequentialGroup().addGroup(indexTabLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(indexTabLayout.createSequentialGroup().addGap(331, 331, 331).addComponent(
										indexButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
								.addGroup(indexTabLayout.createSequentialGroup().addGap(377, 377, 377)
										.addComponent(readyLabel))
								.addGroup(indexTabLayout.createSequentialGroup().addGap(51, 51, 51)
										.addGroup(indexTabLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 647,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(indexTabLayout.createSequentialGroup()
														.addComponent(directoryLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(fullPath, GroupLayout.PREFERRED_SIZE, 639,
																GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(selectDirectory, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 40, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.TRAILING, indexTabLayout.createSequentialGroup()
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(eraseLibrary)))
						.addContainerGap()));
		indexTabLayout.setVerticalGroup(indexTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(indexTabLayout.createSequentialGroup().addContainerGap()
						.addGroup(indexTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(eraseLibrary))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
						.addGroup(indexTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
								GroupLayout.Alignment.TRAILING,
								indexTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(fullPath, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(directoryLabel, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(selectDirectory, GroupLayout.Alignment.TRAILING,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(43, 43, 43)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(readyLabel).addGap(33, 33, 33)
						.addComponent(indexButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGap(97, 97, 97)));
		fullPath.getAccessibleContext().setAccessibleName("fullPath");

		tabs.addTab("Index", indexTab);

		searchTab.setBackground(new Color(255, 255, 255));

		wordToSearch.setFont(new Font("Century Gothic", 0, 14)); // NOI18N

		searchLabel.setFont(new Font("Century Gothic", 0, 18)); // NOI18N
		searchLabel.setText("Word to search:");

		searchButton.setBackground(new Color(255, 255, 255));
		searchButton.setFont(new Font("Century Gothic", 0, 20)); // NOI18N
		searchButton.setText("Search");
		searchButton.setBorder(new LineBorder(new Color(153, 0, 51), 1, true));
		searchButton.setContentAreaFilled(false);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				searchButtonActionPerformed(evt);
			}
		});

		jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		jPanel2.setBackground(new Color(255, 255, 255));

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);

		jScrollPane1.setViewportView(jPanel2);
		jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

		GroupLayout searchTabLayout = new GroupLayout(searchTab);
		searchTab.setLayout(searchTabLayout);
		searchTabLayout.setHorizontalGroup(searchTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				searchTabLayout.createSequentialGroup().addContainerGap(72, Short.MAX_VALUE).addGroup(searchTabLayout
						.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addGroup(searchTabLayout.createSequentialGroup().addComponent(searchLabel).addGap(18, 18, 18)
								.addComponent(wordToSearch, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(searchButton,
										GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
						.addGap(58, 58, 58)));
		searchTabLayout.setVerticalGroup(searchTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(searchTabLayout.createSequentialGroup().addGap(19, 19, 19)
						.addGroup(searchTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(searchLabel)
								.addComponent(wordToSearch, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(searchButton, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
						.addGap(28, 28, 28)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
						.addGap(32, 32, 32)));

		tabs.addTab("Search", searchTab);

		jPanel1.setBackground(new Color(119, 0, 3));

		jLabel1.setFont(new Font("Century Gothic", 0, 16)); // NOI18N
		jLabel1.setForeground(new Color(255, 255, 255));
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setText("Search Engine by David Alvarado");

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tabs)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1,
						GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap(38, Short.MAX_VALUE).addComponent(tabs,
								GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup().addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 491, Short.MAX_VALUE))));

		tabs.getAccessibleContext().setAccessibleName("tabs");

		pack();
	}

}
