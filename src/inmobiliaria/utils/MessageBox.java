package inmobiliaria.utils;

import inmobiliaria.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageBox {
	
	public MessageBox(final Stage primaryStage, final String mensaje){
	
	   // initialize the confirmation dialog
    final Stage dialog = new Stage(StageStyle.TRANSPARENT);
    dialog.initModality(Modality.WINDOW_MODAL);
    dialog.initOwner(primaryStage);
    dialog.setScene(
      new Scene(
        VBoxBuilder.create().styleClass("modal-dialog").children(
          LabelBuilder.create().text(mensaje).build(),
          ButtonBuilder.create().text("Aceptar").defaultButton(true).onAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
              // take action and close the dialog.
              System.out.println("Liked: " + mensaje);
              primaryStage.getScene().getRoot().setEffect(null);
              dialog.close();
            }
          }).build()
// El otro boton          
//          ,
//          ButtonBuilder.create().text("No").cancelButton(true).onAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent actionEvent) {
//              // abort action and close the dialog.
//              System.out.println("Disliked: " + mensaje);
//              primaryStage.getScene().getRoot().setEffect(null);
//              dialog.close();
//            }
//          }
//          ).build()
        ).build()
        , Color.TRANSPARENT
      )
    );
    dialog.getScene().getStylesheets().add(getClass().getResource("/styles/modal-dialog.css").toExternalForm());
 
    // allow the dialog to be dragged around.
    final Node root = dialog.getScene().getRoot();
    final Delta dragDelta = new Delta();
    root.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        // record a delta distance for the drag and drop operation.
        dragDelta.x = dialog.getX() - mouseEvent.getScreenX();
        dragDelta.y = dialog.getY() - mouseEvent.getScreenY();
      }
    });
    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        dialog.setX(mouseEvent.getScreenX() + dragDelta.x);
        dialog.setY(mouseEvent.getScreenY() + dragDelta.y);
      }
    });
 
    dialog.show();
    App.getInstance().getScene().getRoot().setEffect(new BoxBlur());
    // show the confirmation dialog each time a new page is loaded.
//    primaryStage.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
//      @Override public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State newState) {
//        if (newState.equals(Worker.State.SUCCEEDED)) {
//          primaryStage.getScene().getRoot().setEffect(new BoxBlur());
//        }
//      }
//    });
	}
	class Delta { double x, y; }
}
