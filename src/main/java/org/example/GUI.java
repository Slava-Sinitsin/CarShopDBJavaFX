package org.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.daos.*;
import org.example.mappers.*;
import org.example.utils.DateUtil;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class GUI extends Application {
    Session session = null;
    Button transferInfoListButton = new Button("Print transfer info table");
    Button employeeListButton = new Button("Print employee table");
    Button usedInfoListButton = new Button("Print used info table");
    Button partListButton = new Button("Print part table");
    Button carListButton = new Button("Print car table");
    Button certifyingDocumentListButton = new Button("Print certifying document table");
    Button passportListButton = new Button("Print passport table");
    Button clientsBuyerListButton = new Button("Print clients buyer table");
    Button clientsSellerListButton = new Button("Print clients seller table");
    Button addCarButton = new Button("Add car");
    RadioButton newRadioButton = new RadioButton("New");
    RadioButton usedRadioButton = new RadioButton("Used");
    ToggleGroup carTypeGroup = new ToggleGroup();
    Button sellCarButton = new Button("Sell car");
    Button addUsedInfoButton = new Button("Add used info");
    Button AddNewCertifyingDocumentButton = new Button("Add certifying document");
    Button repairCarButton = new Button("Repair car");
    Button addPartButton = new Button("Add part");
    Button addNewEmployeeButton = new Button("Add new employee");
    Button changeEmployeePositionButton = new Button("Change employee's position");
    TextArea responseTextArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        session = HibernateUtil.getSessionFactory().openSession();
        Group group = new Group();
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Lab2");
        stage.setWidth(1760);
        stage.setHeight(780);

        transferInfoListButton.setLayoutX(20);
        transferInfoListButton.setLayoutY(20);

        employeeListButton.setLayoutX(20);
        employeeListButton.setLayoutY(60);

        usedInfoListButton.setLayoutX(20);
        usedInfoListButton.setLayoutY(100);

        partListButton.setLayoutX(20);
        partListButton.setLayoutY(140);

        carListButton.setLayoutX(20);
        carListButton.setLayoutY(180);

        certifyingDocumentListButton.setLayoutX(20);
        certifyingDocumentListButton.setLayoutY(220);

        passportListButton.setLayoutX(20);
        passportListButton.setLayoutY(260);

        clientsBuyerListButton.setLayoutX(20);
        clientsBuyerListButton.setLayoutY(300);

        clientsSellerListButton.setLayoutX(20);
        clientsSellerListButton.setLayoutY(340);

        addCarButton.setLayoutX(20);
        addCarButton.setLayoutY(415);

        sellCarButton.setLayoutX(20);
        sellCarButton.setLayoutY(455);

        addUsedInfoButton.setLayoutX(20);
        addUsedInfoButton.setLayoutY(495);

        AddNewCertifyingDocumentButton.setLayoutX(20);
        AddNewCertifyingDocumentButton.setLayoutY(535);

        repairCarButton.setLayoutX(20);
        repairCarButton.setLayoutY(575);

        addPartButton.setLayoutX(20);
        addPartButton.setLayoutY(615);

        addNewEmployeeButton.setLayoutX(20);
        addNewEmployeeButton.setLayoutY(655);

        changeEmployeePositionButton.setLayoutX(20);
        changeEmployeePositionButton.setLayoutY(695);

        responseTextArea.setLayoutX(250);
        responseTextArea.setLayoutY(20);
        responseTextArea.setEditable(false);
        responseTextArea.setWrapText(true);
        responseTextArea.setPrefSize(1490, 700);

        group.getChildren().addAll(transferInfoListButton, employeeListButton, usedInfoListButton, partListButton,
                carListButton, certifyingDocumentListButton, passportListButton, clientsBuyerListButton,
                clientsSellerListButton, addCarButton, sellCarButton, addUsedInfoButton, AddNewCertifyingDocumentButton,
                repairCarButton, addPartButton, changeEmployeePositionButton, addNewEmployeeButton, responseTextArea);
        stage.show();

        transferInfoListButton.setOnAction(e -> responseTextArea.setText(TransferInfoDao.getAllTransferInfo(session)));
        employeeListButton.setOnAction(e -> responseTextArea.setText(EmployeeDao.getAllEmployees(session)));
        usedInfoListButton.setOnAction(e -> responseTextArea.setText(UsedInfoDao.getAllUsedInfo(session)));
        partListButton.setOnAction(e -> responseTextArea.setText(PartDao.getAllParts(session)));
        carListButton.setOnAction(e -> responseTextArea.setText(CarDao.getAllCars(session)));
        certifyingDocumentListButton.setOnAction(e -> responseTextArea.setText(CertifyingDocumentDao.getAllCertifyingDocuments(session)));
        passportListButton.setOnAction(e -> responseTextArea.setText(PassportDao.getAllPassports(session)));
        clientsBuyerListButton.setOnAction(e -> responseTextArea.setText(ClientBuyerDao.getAllClientBuyers(session)));
        clientsSellerListButton.setOnAction(e -> responseTextArea.setText(ClientSellerDao.getAllClientSellers(session)));
        addCarButton.setOnAction(e -> showCarTypeDialog());
        sellCarButton.setOnAction(e -> startCarSellDialog());
        addUsedInfoButton.setOnAction(e -> addNewUsedInfoDialog());
        AddNewCertifyingDocumentButton.setOnAction(e -> addNewCertifyingDocumentDialog());
        repairCarButton.setOnAction(e -> repairCarDialog());
        addPartButton.setOnAction(e -> addPartDialog());
        addNewEmployeeButton.setOnAction(e -> addNewEmployeeDialog());
        changeEmployeePositionButton.setOnAction(e -> changeEmployeePositionDialog());
    }

    @SuppressWarnings("SameParameterValue")
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showCarTypeDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Selecting a car type");
        alert.setHeaderText(null);
        newRadioButton.setToggleGroup(carTypeGroup);
        usedRadioButton.setToggleGroup(carTypeGroup);
        newRadioButton.setMaxSize(50, 20);
        usedRadioButton.setMaxSize(50, 20);
        GridPane grid = new GridPane();
        grid.addRow(0, new Text(""));
        grid.addRow(1, new Text("           "), newRadioButton, new Text("       "), usedRadioButton);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                if (((RadioButton) carTypeGroup.getSelectedToggle()).getText().equals("New")) {
                    addNewCarDialog();
                } else if (((RadioButton) carTypeGroup.getSelectedToggle()).getText().equals("Used")) {
                    usedInfoDialog();
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void addNewCarDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New car adding");
        alert.setHeaderText(null);
        TextField nameTextField = new TextField();
        TextField colorTextField = new TextField();
        TextField engineNumberTextField = new TextField();
        TextField regNumberTextField = new TextField();
        TextField bodyNumberTextField = new TextField();
        TextField chassisNumberTextField = new TextField();
        TextField releaseDateTextField = new TextField();
        TextField mileageTextField = new TextField();
        TextField releasePriceTextField = new TextField();
        TextField salesPriceTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Name of car:"), nameTextField);
        grid.addRow(1, new Text("Color:"), colorTextField);
        grid.addRow(2, new Text("Engine number:"), engineNumberTextField);
        grid.addRow(3, new Text("Reg number:"), regNumberTextField);
        grid.addRow(4, new Text("Body number:"), bodyNumberTextField);
        grid.addRow(5, new Text("Chassis number:"), chassisNumberTextField);
        grid.addRow(6, new Text("Release date (like 2001-01-01):"), releaseDateTextField);
        grid.addRow(7, new Text("Mileage:"), mileageTextField);
        grid.addRow(8, new Text("Release price:"), releasePriceTextField);
        grid.addRow(9, new Text("Sales price:"), salesPriceTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    CarDao.addCar(session, new Car(
                            1,
                            nameTextField.getText(),
                            colorTextField.getText(),
                            engineNumberTextField.getText(),
                            regNumberTextField.getText(),
                            bodyNumberTextField.getText(),
                            chassisNumberTextField.getText(),
                            DateUtil.parseDate(releaseDateTextField.getText()),
                            Integer.parseInt(mileageTextField.getText()),
                            Integer.parseInt(releasePriceTextField.getText()),
                            Integer.parseInt(salesPriceTextField.getText())
                    ));
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void usedInfoDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Used car adding");
        alert.setHeaderText(null);
        TextField usedInfoTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text(""));
        grid.addRow(1, new Text("Acceptance document number:       "), usedInfoTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                if (!UsedInfoDao.doesUsedInfoExist(session, Integer.parseInt(usedInfoTextField.getText()))) {
                    addUsedInfoDialog();
                } else {
                    passportDialog(Integer.parseInt(usedInfoTextField.getText()));
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void addUsedInfoDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Used info adding");
        alert.setHeaderText(null);
        ComboBox<Employee> employeeComboBox = new ComboBox<>();
        TextField purchasePriceTextField = new TextField();
        TextField certificateDateTextField = new TextField();
        employeeComboBox.setMinWidth(150);
        employeeComboBox.setVisibleRowCount(10);
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Select employee"), new Text("     "), new Text("Purchase price:"), new Text("     "), new Text("Certificate date (like 2001-01-01):"));
        grid.addRow(1, employeeComboBox, new Text("     "), purchasePriceTextField, new Text("     "), certificateDateTextField);
        alert.getDialogPane().setContent(grid);
        List<Employee> allEmployees = EmployeeDao.getAllEmployeesList(session);
        allEmployees.remove(0);
        employeeComboBox.getItems().addAll(allEmployees);
        employeeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                purchasePriceTextField.clear();
                certificateDateTextField.clear();
            }
        });
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Employee selectedEmployee = employeeComboBox.getValue();
                try {
                    UsedInfo usedInfo = new UsedInfo(
                            selectedEmployee.getId(),
                            Integer.parseInt(purchasePriceTextField.getText()),
                            DateUtil.parseDate(certificateDateTextField.getText())
                    );
                    UsedInfoDao.addUsedInfo(session, usedInfo);
                    passportDialog(usedInfo.getId());
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void passportDialog(Integer usedInfoId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Used car adding");
        alert.setHeaderText(null);
        TextField paasportIdTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text(""));
        grid.addRow(1, new Text("Seller's passport ID:       "), paasportIdTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                if (!PassportDao.doesPassportExist(session, Integer.parseInt(paasportIdTextField.getText()))) {
                    addNewPassportAddCarDialog(usedInfoId, Integer.parseInt(paasportIdTextField.getText()));
                } else {
                    certifyingDocumentDialog(usedInfoId, Integer.parseInt(paasportIdTextField.getText()));
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void addNewPassportAddCarDialog(Integer usedInfoId, Integer passportId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add new customer");
        alert.setHeaderText(null);
        TextField firstNameTextField = new TextField();
        TextField secondNameTextField = new TextField();
        TextField middleNameTextField = new TextField();
        TextField birthDateTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField issueDateTextField = new TextField();
        TextField genderTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("                         Add new customer information"));
        grid.addRow(1, new Text("First name:"), firstNameTextField);
        grid.addRow(2, new Text("Second name:"), secondNameTextField);
        grid.addRow(3, new Text("Middle name:"), middleNameTextField);
        grid.addRow(4, new Text("Birth date (like 2001-01-01):"), birthDateTextField);
        grid.addRow(5, new Text("Address:"), addressTextField);
        grid.addRow(6, new Text("Issue date (like 2001-01-01):"), issueDateTextField);
        grid.addRow(7, new Text("Gender (man, woman):"), genderTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Passport passport = new Passport(
                        passportId,
                        firstNameTextField.getText(),
                        secondNameTextField.getText(),
                        middleNameTextField.getText(),
                        DateUtil.parseDate(birthDateTextField.getText()),
                        addressTextField.getText(),
                        DateUtil.parseDate(issueDateTextField.getText()),
                        genderTextField.getText()
                );
                PassportDao.addPassport(session, passport);
                certifyingDocumentDialog(usedInfoId, passportId);
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void certifyingDocumentDialog(Integer usedInfoId, Integer passportId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Used car adding");
        alert.setHeaderText(null);
        TextField certifyingDocumentIdTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text(""));
        grid.addRow(1, new Text("Certifying document ID:       "), certifyingDocumentIdTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                if (!CertifyingDocumentDao.doesCertifyingDocumentExist(session, Integer.parseInt(certifyingDocumentIdTextField.getText()))) {
                    addCertifyingDocumentAddCarDialog(usedInfoId, passportId, Integer.parseInt(certifyingDocumentIdTextField.getText()));
                } else {
                    finalAddUsedCarDialog(usedInfoId, passportId, Integer.parseInt(certifyingDocumentIdTextField.getText()));
                }
            }
        });
    }

    private void addCertifyingDocumentAddCarDialog(Integer usedInfoId, Integer passportId, Integer certifyingDocumentId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add certifying document");
        alert.setHeaderText(null);
        TextField nameTextField = new TextField();
        TextField issueDateTextField = new TextField();
        TextField issuerTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("                         Certifying document, add a new one"));
        grid.addRow(1, new Text("Name:"), nameTextField);
        grid.addRow(2, new Text("Issue date (like 2001-01-01):"), issueDateTextField);
        grid.addRow(3, new Text("Issuer:"), issuerTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                CertifyingDocument certifyingDocument = new CertifyingDocument(
                        certifyingDocumentId,
                        nameTextField.getText(),
                        DateUtil.parseDate(issueDateTextField.getText()),
                        issuerTextField.getText()
                );
                CertifyingDocumentDao.addCertifyingDocument(session, certifyingDocument);
                finalAddUsedCarDialog(usedInfoId, passportId, certifyingDocumentId);
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void finalAddUsedCarDialog(Integer usedInfoId, Integer passportId, Integer certifyingDocumentId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Used car adding");
        alert.setHeaderText(null);
        TextField purchaseDateTextField = new TextField();
        TextField carNameTextField = new TextField();
        TextField colorTextField = new TextField();
        TextField engineNumberTextField = new TextField();
        TextField regNumberTextField = new TextField();
        TextField bodyNumberTextField = new TextField();
        TextField chassisNumberTextField = new TextField();
        TextField releaseDateTextField = new TextField();
        TextField mileageTextField = new TextField();
        TextField releasePriceTextField = new TextField();
        TextField salesPriceTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Purchase date (like 2001-01-01):"), purchaseDateTextField);
        grid.addRow(1, new Text("Name of car:"), carNameTextField);
        grid.addRow(2, new Text("Color:"), colorTextField);
        grid.addRow(3, new Text("Engine number:"), engineNumberTextField);
        grid.addRow(4, new Text("Reg number:"), regNumberTextField);
        grid.addRow(5, new Text("Body number:"), bodyNumberTextField);
        grid.addRow(6, new Text("Chassis number:"), chassisNumberTextField);
        grid.addRow(7, new Text("Release date (like 2001-01-01):"), releaseDateTextField);
        grid.addRow(8, new Text("Mileage:"), mileageTextField);
        grid.addRow(9, new Text("Release price:"), releasePriceTextField);
        grid.addRow(10, new Text("Sales price:"), salesPriceTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    Car car = new Car(
                            usedInfoId,
                            carNameTextField.getText(),
                            colorTextField.getText(),
                            engineNumberTextField.getText(),
                            regNumberTextField.getText(),
                            bodyNumberTextField.getText(),
                            chassisNumberTextField.getText(),
                            DateUtil.parseDate(releaseDateTextField.getText()),
                            Integer.parseInt(mileageTextField.getText()),
                            Integer.parseInt(releasePriceTextField.getText()),
                            Integer.parseInt(salesPriceTextField.getText())
                    );
                    CarDao.addCar(session, car);
                    ClientSellerDao.addClientSeller(session, new ClientSeller(
                            passportId,
                            car.getId(),
                            certifyingDocumentId,
                            DateUtil.parseDate(purchaseDateTextField.getText())
                    ));
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    private void startCarSellDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sell car");
        alert.setHeaderText(null);
        TextField passportIdTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text(""));
        grid.addRow(1, new Text("Passport ID:     "), passportIdTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    if (!PassportDao.doesPassportExist(session, Integer.parseInt(passportIdTextField.getText()))) {
                        addNewPassportDialog(Integer.parseInt(passportIdTextField.getText()));
                    } else {
                        finalCarSellDialog(Integer.parseInt(passportIdTextField.getText()));
                    }
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void addNewPassportDialog(Integer passportId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add new customer");
        alert.setHeaderText(null);
        TextField firstNameTextField = new TextField();
        TextField secondNameTextField = new TextField();
        TextField middleNameTextField = new TextField();
        TextField birthDateTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField issueDateTextField = new TextField();
        TextField genderTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("                         Add new customer information"));
        grid.addRow(1, new Text("First name:"), firstNameTextField);
        grid.addRow(2, new Text("Second name:"), secondNameTextField);
        grid.addRow(3, new Text("Middle name:"), middleNameTextField);
        grid.addRow(4, new Text("Birth date (like 2001-01-01):"), birthDateTextField);
        grid.addRow(5, new Text("Address:"), addressTextField);
        grid.addRow(6, new Text("Issue date (like 2001-01-01):"), issueDateTextField);
        grid.addRow(7, new Text("Gender (man, woman):"), genderTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Passport passport = new Passport(
                        passportId,
                        firstNameTextField.getText(),
                        secondNameTextField.getText(),
                        middleNameTextField.getText(),
                        DateUtil.parseDate(birthDateTextField.getText()),
                        addressTextField.getText(),
                        DateUtil.parseDate(issueDateTextField.getText()),
                        genderTextField.getText()
                );
                PassportDao.addPassport(session, passport);
                finalCarSellDialog(passport.getId());
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void finalCarSellDialog(Integer passportId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sell car");
        alert.setHeaderText(null);
        ComboBox<Car> carComboBox = new ComboBox<>();
        TextField saleDateTextField = new TextField();
        TextField accounNumbeTextField = new TextField();
        TextField paymentTypeTextField = new TextField();
        carComboBox.setPrefWidth(200);
        carComboBox.setVisibleRowCount(10);
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Select car"), new Text("     "), new Text("Sale date (like 2001-01-01):"), new Text("     "), new Text("Account number:"), new Text("     "), new Text("Payment type (cash, card, transfer):"));
        grid.addRow(1, carComboBox, new Text("     "), saleDateTextField, new Text("     "), accounNumbeTextField, new Text("     "), paymentTypeTextField);
        alert.getDialogPane().setContent(grid);
        List<Car> unsoldCars = CarDao.getUnsoldCars(session);
        carComboBox.getItems().addAll(unsoldCars);
        carComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                saleDateTextField.clear();
                accounNumbeTextField.clear();
                paymentTypeTextField.clear();
            }
        });
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK && saleDateTextField.getText() != null && accounNumbeTextField.getText() != null && paymentTypeTextField.getText() != null) {
                Car selectedCar = carComboBox.getValue();
                try {
                    ClientBuyerDao.addClientBuyer(session, new ClientBuyer(
                            passportId,
                            selectedCar.getId(),
                            DateUtil.parseDate(saleDateTextField.getText()),
                            Long.parseLong(accounNumbeTextField.getText()),
                            paymentTypeTextField.getText()
                    ));
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void addNewUsedInfoDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Used info adding");
        alert.setHeaderText(null);
        ComboBox<Employee> employeeComboBox = new ComboBox<>();
        TextField purchasePriceTextField = new TextField();
        TextField certificateDateTextField = new TextField();
        employeeComboBox.setMinWidth(150);
        employeeComboBox.setVisibleRowCount(10);
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Select employee"), new Text("     "), new Text("Purchase price:"), new Text("     "), new Text("Certificate date (like 2001-01-01):"));
        grid.addRow(1, employeeComboBox, new Text("     "), purchasePriceTextField, new Text("     "), certificateDateTextField);
        alert.getDialogPane().setContent(grid);
        List<Employee> allEmployees = EmployeeDao.getAllEmployeesList(session);
        allEmployees.remove(0);
        employeeComboBox.getItems().addAll(allEmployees);
        employeeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                purchasePriceTextField.clear();
                certificateDateTextField.clear();
            }
        });
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Employee selectedEmployee = employeeComboBox.getValue();
                try {
                    UsedInfo usedInfo = new UsedInfo(
                            selectedEmployee.getId(),
                            Integer.parseInt(purchasePriceTextField.getText()),
                            DateUtil.parseDate(certificateDateTextField.getText())
                    );
                    UsedInfoDao.addUsedInfo(session, usedInfo);
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    private void addNewCertifyingDocumentDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add certifying document");
        alert.setHeaderText(null);
        TextField idTextField = new TextField();
        TextField nameTextField = new TextField();
        TextField issueDateTextField = new TextField();
        TextField issuerTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text(""));
        grid.addRow(1, new Text("ID:"), idTextField);
        grid.addRow(2, new Text("Name:"), nameTextField);
        grid.addRow(3, new Text("Issue date (like 2001-01-01):"), issueDateTextField);
        grid.addRow(4, new Text("Issuer:"), issuerTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    CertifyingDocument certifyingDocument = new CertifyingDocument(
                            Integer.parseInt(idTextField.getText()),
                            nameTextField.getText(),
                            DateUtil.parseDate(issueDateTextField.getText()),
                            issuerTextField.getText()
                    );
                    CertifyingDocumentDao.addCertifyingDocument(session, certifyingDocument);
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    private void repairCarDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setPrefWidth(500);
        alert.setTitle("Repair car");
        alert.setHeaderText(null);
        ComboBox<Car> carComboBox = new ComboBox<>();
        ComboBox<Part> partsComboBox = new ComboBox<>();
        carComboBox.setPrefWidth(200);
        carComboBox.setVisibleRowCount(10);
        partsComboBox.setPrefWidth(200);
        partsComboBox.setVisibleRowCount(10);
        List<Car> allCars = CarDao.getAllCarsList(session);
        carComboBox.getItems().addAll(allCars);
        carComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                partsComboBox.getItems().clear();
                List<Part> partsForSelectedCar = PartDao.getPartsForCar(session, newValue.getId());
                partsComboBox.getItems().setAll(partsForSelectedCar);
            }
        });
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Select car"), new Text("     "), new Text("Select part"));
        grid.addRow(1, carComboBox, new Text("     "), partsComboBox);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Car selectedCar = carComboBox.getValue();
                Part selectedPart = partsComboBox.getValue();
                if (selectedCar != null && selectedPart != null) {
                    selectedPart.setCount(selectedPart.getCount() - 1);
                    if (selectedPart.getCount() == 0) {
                        PartDao.deletePart(session, selectedPart.getId());
                    } else {
                        PartDao.updatePart(session, selectedPart);
                    }
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void addPartDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setPrefWidth(500);
        alert.setTitle("Add part");
        alert.setHeaderText(null);
        ComboBox<Car> carComboBox = new ComboBox<>();
        TextField nameTextField = new TextField();
        TextField priceTextField = new TextField();
        TextField countTextField = new TextField();
        carComboBox.setPrefWidth(200);
        carComboBox.setVisibleRowCount(10);
        nameTextField.setPrefWidth(200);
        List<Car> allCars = CarDao.getAllCarsList(session);
        carComboBox.getItems().addAll(allCars);
        carComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameTextField.clear();
                priceTextField.clear();
                countTextField.clear();
            }
        });
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Select car"), new Text("     "), new Text("Name:"), new Text("     "), new Text("Price:"), new Text("     "), new Text("Count:"));
        grid.addRow(1, carComboBox, new Text("     "), nameTextField, new Text("     "), priceTextField, new Text("     "), countTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Car selectedCar = carComboBox.getValue();
                try {
                    if (selectedCar != null && nameTextField.getText() != null && priceTextField.getText() != null && countTextField.getText() != null) {
                        PartDao.addPart(session, new Part(
                                selectedCar.getId(),
                                nameTextField.getText(),
                                Integer.parseInt(priceTextField.getText()),
                                Integer.parseInt(countTextField.getText())
                        ));
                    }
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    @SuppressWarnings("DuplicatedCode")
    private void addNewEmployeeDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New employee adding");
        alert.setHeaderText(null);
        TextField firstNameTextField = new TextField();
        TextField secondNameTextField = new TextField();
        TextField middleNameTextField = new TextField();
        TextField birthDateTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField positionTextField = new TextField();
        TextField salaryTextField = new TextField();
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("First name:"), firstNameTextField);
        grid.addRow(1, new Text("Second name:"), secondNameTextField);
        grid.addRow(2, new Text("Middle name:"), middleNameTextField);
        grid.addRow(4, new Text("Birth date (like 2001-01-01):"), birthDateTextField);
        grid.addRow(5, new Text("Address:"), addressTextField);
        grid.addRow(6, new Text("Position:"), positionTextField);
        grid.addRow(7, new Text("Salary:"), salaryTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    EmployeeDao.addEmployee(session, new Employee(
                            1,
                            firstNameTextField.getText(),
                            secondNameTextField.getText(),
                            middleNameTextField.getText(),
                            DateUtil.parseDate(birthDateTextField.getText()),
                            addressTextField.getText(),
                            positionTextField.getText(),
                            Integer.parseInt(salaryTextField.getText())
                    ));
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }

    private void changeEmployeePositionDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setPrefWidth(500);
        alert.setTitle("Change employee's position");
        alert.setHeaderText(null);
        ComboBox<Employee> employeeComboBox = new ComboBox<>();
        TextField newPositionTextField = new TextField();
        TextField newSalaryTextField = new TextField();
        TextField transferReasonTextField = new TextField();
        TextField orderDateTextField = new TextField();
        employeeComboBox.setMinWidth(150);
        employeeComboBox.setVisibleRowCount(10);
        List<Employee> allEmployees = EmployeeDao.getAllEmployeesList(session);
        allEmployees.remove(0);
        employeeComboBox.getItems().addAll(allEmployees);
        employeeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newPositionTextField.clear();
                newSalaryTextField.clear();
                transferReasonTextField.clear();
                orderDateTextField.clear();
            }
        });
        GridPane grid = new GridPane();
        grid.addRow(0, new Text("Select employee"), new Text("     "), new Text("New position:"), new Text("     "), new Text("New salary:"), new Text("     "), new Text("Transfer reason:"), new Text("     "), new Text("Order date (like 2001-01-01):"));
        grid.addRow(1, employeeComboBox, new Text("     "), newPositionTextField, new Text("     "), newSalaryTextField, new Text("     "), transferReasonTextField, new Text("     "), orderDateTextField);
        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                Employee selectedEmployee = employeeComboBox.getValue();
                try {
                    if (selectedEmployee != null && newPositionTextField.getText() != null && newSalaryTextField.getText() != null && transferReasonTextField.getText() != null && orderDateTextField.getText() != null) {
                        TransferInfo transferInfo = new TransferInfo(
                                selectedEmployee.getPosition(),
                                transferReasonTextField.getText(),
                                DateUtil.parseDate(orderDateTextField.getText())
                        );
                        TransferInfoDao.addTransferInfo(session, transferInfo);
                        EmployeeDao.addEmployee(session, new Employee(
                                transferInfo.getId(),
                                selectedEmployee.getFirstName(),
                                selectedEmployee.getSecondName(),
                                selectedEmployee.getMiddleName(),
                                selectedEmployee.getBirthDate(),
                                selectedEmployee.getAddress(),
                                newPositionTextField.getText(),
                                Integer.parseInt(newSalaryTextField.getText())
                        ));
                    }
                } catch (NumberFormatException e) {
                    showErrorAlert("Input Error!");
                }
            }
        });
    }
}