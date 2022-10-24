package com.nklymok;

import com.nklymok.alert.AlertFactory;
import com.nklymok.constant.Constants;
import com.nklymok.model.Person;
import com.nklymok.model.PersonFormInput;
import com.nklymok.model.Sex;
import com.nklymok.util.FileUtil;
import com.nklymok.util.SelectionUtil;
import com.nklymok.util.SortingUtil;
import com.nklymok.util.ValidationUtil;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrimaryController {

    @FXML
    public TableColumn<Person, String> noColumn;
    @FXML
    public TableColumn<Person, String> firstNameColumn;
    @FXML
    public TableColumn<Person, String> lastNameColumn;
    @FXML
    public TableColumn<Person, String> birthDateColumn;
    @FXML
    public TableColumn<Person, String> sexColumn;
    @FXML
    public TableColumn<Person, String> temperatureColumn;
    @FXML
    public TableColumn<Person, String> hbColumn;
    @FXML
    public TextField txtFirstName;
    @FXML
    public TextField txtLastName;
    @FXML
    public DatePicker datePicker;
    @FXML
    public ComboBox<Sex> cbSex;
    @FXML
    public TextField txtTemperature;
    @FXML
    public TextField txtHb;
    @FXML
    public TableView<Person> personTable;
    @FXML
    public BorderPane root;

    @FXML
    private TextArea txtLogs;
    private List<Person> people = new ArrayList<>();


    @FXML
    public void initialize() {
        noColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(personTable.getItems().indexOf(p.getValue()) + 1 + ""));
        noColumn.setSortable(false);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        hbColumn.setCellValueFactory(new PropertyValueFactory<>("hemoglobinLevel"));
        cbSex.getItems().add(Sex.MALE);
        cbSex.getItems().add(Sex.FEMALE);
        cbSex.setValue(Sex.MALE);
    }

    @FXML
    public void createPerson() {
        PersonFormInput personFormInput = PersonFormInput
                .builder()
                .firstName(txtFirstName.getText())
                .lastName(txtLastName.getText())
                .sex(cbSex.getValue())
                .temperature(txtTemperature.getText())
                .hemoglobinLevel(txtHb.getText())
                .birthDate(datePicker.getValue())
                .build();
        var exceptions = ValidationUtil.validateFormInput(personFormInput);
        if (!exceptions.isEmpty()) {
            AlertFactory.getCustomErrorAlert(ValidationUtil.formattedErrors(exceptions)).show();
            return;
        }
        people.add(personFormInput.toPerson());
        reset();
    }

    @FXML
    private void reset() {
        personTable.getItems().clear();
        people.forEach(p -> personTable.getItems().add(p));
    }

    @FXML
    private void sortByTemperature() {
        personTable.setItems(SortingUtil.personCountingSort(personTable.getItems()));
    }

    @FXML
    private void femalesWithHighHb() {
        personTable.setItems(SelectionUtil.selectFemalesWithHigherHemoglobin(personTable.getItems()));
    }

    @FXML
    private void youngestMalesWithNormalTemperatureAndHighHb() {
        personTable.setItems(SelectionUtil.selectYoungestMalesWithNormalTemperatureAndHigherHemoglobin(personTable.getItems()));
    }

    @FXML
    private void olderThan40WithHighHbAndLowTemperature() {
        personTable.setItems(SelectionUtil.selectOlderThan40WithHigherHbAndLowerTemperature(personTable.getItems()));
    }

    @FXML
    private void oldestFemalesWithRepeatedNameAndNormalLevelHb() {
        personTable.setItems(SelectionUtil.findOldestFemalesWithSameNameAndNormalHb(personTable.getItems()));
    }

    @FXML
    private void greetYoungerThan28WithNormalTemperature() {
        ObservableList<Person> people = SelectionUtil.findBelow28WithNormalTemperature(personTable.getItems());
        personTable.setItems(people);
        txtLogs.setText(congratulate(people));
    }

    private String congratulate(ObservableList<Person> people) {
        return people.stream()
                .map(this::congratulate)
                .collect(Collectors.joining());
    }

    private String congratulate(Person person) {
        return String.format(Constants.LogTemplate.GREETING, person.getFirstName(), person.getLastName());
    }

    @FXML
    private void exportPeople() {
        FileChooser fileChooser = new FileChooser();
        boolean success = FileUtil.serializePeople(personTable.getItems(), fileChooser.showSaveDialog(root.getScene().getWindow()));
        if (!success) {
            AlertFactory.getExportFailedAlert().show();
        } else {
            AlertFactory.getExportSuccessAlert().show();
        }
    }

    @FXML
    private void importPeople() {
        FileChooser fileChooser = new FileChooser();
        List<Person> imported = FileUtil.deserializePeople(fileChooser.showOpenDialog(root.getScene().getWindow()));
        Alert alert;
        if (imported.isEmpty()) {
            alert = AlertFactory.getImportFailedAlert();
        } else {
            people = imported;
            reset();
            alert = AlertFactory.getImportSuccessAlert();
        }
        alert.show();
    }

    @FXML
    private void clear() {
        people.clear();
        reset();
        txtLogs.clear();
    }

    @FXML
    private void close() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void about() {
        AlertFactory.getAboutAlert().show();
    }
}
