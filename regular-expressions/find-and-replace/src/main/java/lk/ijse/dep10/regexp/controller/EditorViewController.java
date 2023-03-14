package lk.ijse.dep10.regexp.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.dep10.regexp.util.SearchResult;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorViewController {

    public Button btnDown;
    public Button btnReplace;
    public Button btnReplaceAll;
    public Button btnUp;
    public Label lblResults;
    public TextArea txtEditor;
    public TextField txtFind;
    public TextField txtReplace;
    public CheckBox chkMatchCase;
    private ArrayList<SearchResult> searchResultList = new ArrayList<>();
    private int pos = 0;

    public void initialize() {
        txtFind.textProperty().addListener((ov, previous, current) -> calculateSearchResult());
        txtEditor.textProperty().addListener((ov, previous, current) -> calculateSearchResult());
    }

    private void calculateSearchResult() {
        String query = txtFind.getText();
        searchResultList.clear();
        pos = 0;
        txtEditor.deselect();

        Pattern pattern;
        try {
            pattern = Pattern.compile(query);
        }catch (RuntimeException e){
            return;
        }
        Matcher matcher = pattern.matcher(txtEditor.getText());

        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            SearchResult result = new SearchResult(start, end);
            searchResultList.add(result);
        }

        lblResults.setText(String.format("%d Results", searchResultList.size()));

        select();
    }

    private void select(){
        if (searchResultList.isEmpty()) return;
        SearchResult searchResult = searchResultList.get(pos);
        txtEditor.selectRange(searchResult.getStart(), searchResult.getEnd());
        lblResults.setText(String.format("%d/%d Results", (pos + 1), searchResultList.size()));
    }

    public void btnDownOnAction(ActionEvent event) {
        pos++;
        if (pos == searchResultList.size()){
            pos = -1;
            return;
        }
        select();
    }

    public void btnUpOnAction(ActionEvent event) {
        pos--;
        if (pos < 0){
            pos = searchResultList.size();
            return;
        }
        select();
    }

    public void btnReplaceAllOnAction(ActionEvent event) {

    }

    public void btnReplaceOnAction(ActionEvent event) {

    }

    public void chkMatchCaseOnAction(ActionEvent actionEvent) {
    }
}
