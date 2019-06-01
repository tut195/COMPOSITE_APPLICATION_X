package com.babenkovladimir.composite_application_x.abdroid_base_materials.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.babenkovladimir.composite_application_x.R;

public class AddEditNoteActivity extends AppCompatActivity {

  Note note;
  private static final int MODE_CREATE = 1;
  private static final int MODE_EDIT = 2;

  private int mode;
  private EditText textTitle;
  private EditText textContent;

  private boolean needRefresh;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_edit_node);
    this.textTitle = (EditText) this.findViewById(R.id.text_note_title);
    this.textContent = (EditText) this.findViewById(R.id.text_note_content);

    Intent intent = this.getIntent();
    this.note = (Note) intent.getSerializableExtra("note");
    if (note == null) {
      this.mode = MODE_CREATE;
    } else {
      this.mode = MODE_EDIT;
      this.textTitle.setText(note.getNoteTitle());
      this.textContent.setText(note.getNoteContent());
    }
  }

  // User Click on the Save button.
  public void buttonSaveClicked(View view) {
    DatabaseHelper db = new DatabaseHelper(this);

    String title = this.textTitle.getText().toString();
    String content = this.textContent.getText().toString();

    if (title.equals("") || content.equals("")) {
      Toast.makeText(getApplicationContext(),
          "Please enter title & content", Toast.LENGTH_LONG).show();
      return;
    }

    if (mode == MODE_CREATE) {
      this.note = new Note(title, content);
      db.addNote(note);
    } else {
      this.note.setNoteTitle(title);
      this.note.setNoteContent(content);
      db.updateNote(note);
    }

    this.needRefresh = true;

    // Back to MainActivity.
    this.onBackPressed();
  }

  // User Click on the Cancel button.
  public void buttonCancelClicked(View view) {
    // Do nothing, back MainActivity.
    this.onBackPressed();
  }

  // When completed this Activity,
  // Send feedback to the Activity called it.
  @Override
  public void finish() {

    // Create Intent
    Intent data = new Intent();

    // Request MainActivity refresh its ListView (or not).
    data.putExtra("needRefresh", needRefresh);

    // Set Result
    this.setResult(Activity.RESULT_OK, data);
    super.finish();
  }

}