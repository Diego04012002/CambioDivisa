package dad.cambiodivisa;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CambioDivisa extends Application {

	private TextField caja1;
	private TextField caja2;
	private ComboBox<Divisa> divisas;
	private ComboBox<Divisa> divisas2;
	private Button cambio;
	
	public void start(Stage primaryStage) throws Exception {
		
		caja1 = new TextField();
		caja1.setPrefWidth(50);
		caja1.setText("0");
		
		caja2= new TextField();
		caja2.setPrefWidth(50);
		caja2.setEditable(false);
		caja2.setText("0");
		
		divisas= new ComboBox<Divisa>();
		divisas.getItems().addAll(new Divisa("Euro", 1.0),
								  new Divisa("Libra", 0.8873),
								  new Divisa("Dolar", 1.2007),
								  new Divisa("Yen", 133.59));
		divisas.getSelectionModel().selectFirst();
		
		divisas2= new ComboBox<Divisa>();
		divisas2.getItems().addAll(new Divisa("Euro", 1.0),
				  				   new Divisa("Libra", 0.8873),
				  				   new Divisa("Dolar", 1.2007),
				  				   new Divisa("Yen", 133.59));
		divisas2.getSelectionModel().select(new Divisa ("Yen", 133.59));
		
		
		cambio= new Button();
		cambio.setText("Cambiar");
		
		HBox arriba = new HBox();
		arriba.setAlignment(Pos.CENTER);
		arriba.setSpacing(5);
		arriba.getChildren().addAll(caja1, divisas);
		
		HBox medio = new HBox();
		medio.setAlignment(Pos.CENTER);
		medio.setSpacing(5);
		medio.getChildren().addAll(caja2, divisas2);
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(5);
		root.getChildren().addAll(arriba, medio, cambio);
		
		Scene escena = new Scene(root, 320, 240);
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("Cambio Divisa");
		primaryStage.show();
		
		cambio.setOnAction(e->cambioAction(e));
		
	}

	private void cambioAction(ActionEvent e) {
		try {
			Divisa origen=divisas.getSelectionModel().selectedItemProperty().get();
			Divisa destino=divisas2.getSelectionModel().selectedItemProperty().get();
			caja2.textProperty().setValue(String.valueOf(destino.fromEuro(origen.toEuro(Double.parseDouble(caja1.getText())))));
		}catch (Exception e1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Cambio Divisa");
			alert.setHeaderText("ERROR");
			alert.setContentText("Se esperaban numeros para la conversion");
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
