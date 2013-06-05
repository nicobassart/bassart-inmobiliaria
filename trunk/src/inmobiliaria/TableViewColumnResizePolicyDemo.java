package inmobiliaria;

import inmobiliaria.controller.WindowButtons;
import inmobiliaria.controller.WindowResizeButton;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TableViewColumnResizePolicyDemo extends Application {
	private WindowResizeButton windowResizeButton;
	private double mouseDragOffsetX = 0;
	private double mouseDragOffsetY = 0;
	private Stage stage;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		BorderPane root;
		
		stage.initStyle(StageStyle.UNDECORATED);
		
		windowResizeButton = new WindowResizeButton(stage, 800, 600);
		// create root
		root = new BorderPane();

		// add close min max
		final WindowButtons windowButtons = new WindowButtons(stage);

		ToolBar toolBar = new ToolBar();

		toolBar.getItems().add(windowButtons);
		// add window header double clicking
		toolBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					windowButtons.toogleMaximized();
				}
			}
		});
		// add window dragging
		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseDragOffsetX = event.getSceneX();
				mouseDragOffsetY = event.getSceneY();
			}
		});
		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!windowButtons.isMaximized()) {
					stage.setX(event.getScreenX()-mouseDragOffsetX);
					stage.setY(event.getScreenY()-mouseDragOffsetY);
				}
			}
		});
		
		HBox hbox = new HBox();
		hbox.getChildren().add(toolBar);
		root.getChildren().addAll(hbox);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(App.class.getResource("../styles/StyleSheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

	}

//
//	private void configureTable(VBox root) {
//
//		final ObservableList<MyDomain> data = FXCollections
//				.observableArrayList(new MyDomain("Apple", "This is a fruit.",
//						"Red"), new MyDomain("Orange", "This is also a fruit.",
//						"Orange"), new MyDomain("Potato",
//						"This is a vegetable.", "Brown"));
//
//		TableView<MyDomain> table1 = getTableView(data);
//		table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//		TableView<MyDomain> table2 = getTableView(data);
//		table2.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
//
//		root.setSpacing(15);
//		root.getChildren().addAll(
//				LabelBuilder.create().text("CONSTRAINED_RESIZE_POLICY")
//						.style("-fx-font-weight:bold;").build(),
//				table1,
//				LabelBuilder.create().text("UNCONSTRAINED_RESIZE_POLICY")
//						.style("-fx-font-weight:bold;").build(), table2);
//	}
//
//	private TableView<MyDomain> getTableView(ObservableList<MyDomain> data) {
//		TableView<MyDomain> table = new TableView<MyDomain>();
//
//		TableColumn<MyDomain, String> titleColumn = new TableColumn<MyDomain, String>(
//				"Title");
//		titleColumn.setPrefWidth(100);
//		titleColumn
//				.setCellValueFactory(new PropertyValueFactory<MyDomain, String>(
//						"name"));
//
//		TableColumn<MyDomain, String> descCol = new TableColumn<MyDomain, String>(
//				"Description");
//		descCol.setPrefWidth(250);
//		descCol.setCellValueFactory(new PropertyValueFactory<MyDomain, String>(
//				"description"));
//
//		TableColumn<MyDomain, String> colorCol = new TableColumn<MyDomain, String>(
//				"Color");
//		colorCol.setPrefWidth(100);
//		colorCol.setCellValueFactory(new PropertyValueFactory<MyDomain, String>(
//				"color"));
//
//		table.getColumns().addAll(titleColumn, descCol, colorCol);
//		table.setItems(data);
//		return table;
//	}
//
//	/**
//	 * Domain Object.
//	 */
//	public class MyDomain {
//		private SimpleStringProperty name = new SimpleStringProperty();
//		private SimpleStringProperty description = new SimpleStringProperty();
//		private SimpleStringProperty color = new SimpleStringProperty();
//
//		public MyDomain(String name, String desc, String color) {
//			this.name.set(name);
//			this.description.set(desc);
//			this.color.set(color);
//		}
//
//		public String getDescription() {
//			return description.get();
//		}
//
//		public SimpleStringProperty descriptionProperty() {
//			return description;
//		}
//
//		public String getName() {
//			return name.get();
//		}
//
//		public SimpleStringProperty nameProperty() {
//			return name;
//		}
//
//		public String getColor() {
//			return color.get();
//		}
//
//		public SimpleStringProperty colorProperty() {
//			return color;
//		}
//	}
}