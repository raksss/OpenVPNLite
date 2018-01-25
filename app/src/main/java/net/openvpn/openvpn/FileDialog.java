package net.openvpn.openvpn;

import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class FileDialog extends ListActivity {
    public static final String CAN_SELECT_DIR = "CAN_SELECT_DIR";
    private static final String EXT_STORAGE = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String FORMAT_FILTER = "FORMAT_FILTER";
    private static final String ITEM_IMAGE = "image";
    private static final String ITEM_KEY = "key";
    public static final int MODE_CREATE = 0;
    public static final int MODE_OPEN = 1;
    public static final String OPTION_CURRENT_PATH_IN_TITLEBAR = "OPTION_CURRENT_PATH_IN_TITLEBAR";
    public static final String OPTION_ONE_CLICK_SELECT = "OPTION_ONE_CLICK_SELECT";
    public static final String OPTION_PROMPT = "OPTION_PROMPT";
    public static final String RESULT_PATH = "RESULT_PATH";
    private static final String ROOT = "/";
    public static final String SELECTION_MODE = "SELECTION_MODE";
    public static final String START_PATH = "START_PATH";
    private boolean canSelectDir = false;
    private String currentPath = ROOT;
    private String[] formatFilter = null;
    private InputMethodManager inputManager;
    private HashMap<String, Integer> lastPositions = new HashMap();
    private LinearLayout layoutCreate;
    private LinearLayout layoutSelect;
    private EditText mFileName;
    private ArrayList<HashMap<String, Object>> mList;
    private boolean m_bOneClickSelect = false;
    private boolean m_bTitlebarFolder = false;
    private TextView myPath;
    private TextView myPrompt;
    private String parentPath;
    private List<String> path = null;
    private Button selectButton;
    private File selectedFile;
    private int selectionMode = MODE_CREATE;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(MODE_CREATE, getIntent());
        if (OpenVPNClientBase.themeSet) {
            setTheme(OpenVPNClientBase.themeResId);
        }
        setContentView(R.layout.file_dialog_main);
        this.myPath = (TextView) findViewById(R.id.fdPath);
        this.myPrompt = (TextView) findViewById(R.id.fdPrompt);
        this.mFileName = (EditText) findViewById(R.id.fdEditTextFile);
        this.m_bOneClickSelect = getIntent().getBooleanExtra(OPTION_ONE_CLICK_SELECT, this.m_bOneClickSelect);
        this.m_bTitlebarFolder = getIntent().getBooleanExtra(OPTION_CURRENT_PATH_IN_TITLEBAR, this.m_bTitlebarFolder);
        if (this.m_bTitlebarFolder) {
            this.myPath.setVisibility(8);
        }
        String prompt = getIntent().getStringExtra(OPTION_PROMPT);
        if (prompt != null) {
            this.myPrompt.setText(prompt);
            this.myPrompt.setVisibility(MODE_CREATE);
        } else {
            this.myPrompt.setVisibility(8);
        }
        this.inputManager = (InputMethodManager) getSystemService("input_method");
        this.selectButton = (Button) findViewById(R.id.fdButtonSelect);
        this.selectButton.setEnabled(false);
        this.selectButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (FileDialog.this.selectedFile != null) {
                    FileDialog.this.getIntent().putExtra(FileDialog.RESULT_PATH, FileDialog.this.selectedFile.getPath());
                    FileDialog.this.setResult(-1, FileDialog.this.getIntent());
                    FileDialog.this.finish();
                }
            }
        });
        Button newButton = (Button) findViewById(R.id.fdButtonNew);
        newButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FileDialog.this.setCreateVisible(v);
                FileDialog.this.mFileName.setText(BuildConfig.FLAVOR);
                FileDialog.this.mFileName.requestFocus();
            }
        });
        this.selectionMode = getIntent().getIntExtra(SELECTION_MODE, MODE_CREATE);
        this.formatFilter = getIntent().getStringArrayExtra(FORMAT_FILTER);
        this.canSelectDir = getIntent().getBooleanExtra(CAN_SELECT_DIR, false);
        if (this.selectionMode == MODE_OPEN) {
            newButton.setVisibility(8);
        }
        this.layoutSelect = (LinearLayout) findViewById(R.id.fdLinearLayoutSelect);
        this.layoutCreate = (LinearLayout) findViewById(R.id.fdLinearLayoutCreate);
        this.layoutCreate.setVisibility(8);
        if (this.selectionMode == MODE_OPEN && this.m_bOneClickSelect) {
            this.layoutSelect.setVisibility(8);
        }
        ((Button) findViewById(R.id.fdButtonCancel)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FileDialog.this.setResult(FileDialog.MODE_CREATE, FileDialog.this.getIntent());
                FileDialog.this.finish();
            }
        });
        ((Button) findViewById(R.id.fdButtonCreate)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (FileDialog.this.mFileName.getText().length() > 0) {
                    FileDialog.this.getIntent().putExtra(FileDialog.RESULT_PATH, FileDialog.this.currentPath + FileDialog.ROOT + FileDialog.this.mFileName.getText());
                    FileDialog.this.setResult(-1, FileDialog.this.getIntent());
                    FileDialog.this.finish();
                }
            }
        });
        String startPath = getIntent().getStringExtra(START_PATH);
        if (startPath == null) {
            startPath = ROOT;
        }
        if (this.canSelectDir) {
            this.selectedFile = new File(startPath);
            this.selectButton.setEnabled(true);
        }
        getDir(startPath);
    }

    private void getDir(String dirPath) {
        boolean useAutoSelection = dirPath.length() < this.currentPath.length();
        Integer position = (Integer) this.lastPositions.get(this.parentPath);
        getDirImpl(dirPath);
        if (position != null && useAutoSelection) {
            getListView().setSelection(position.intValue());
        }
    }

    private void showLocation(int res_id_prefix, String currentPath) {
        if (this.m_bTitlebarFolder) {
            setTitle(currentPath);
        } else {
            this.myPath.setText(getText(res_id_prefix) + ": " + currentPath);
        }
    }

    private void getDirImpl(String dirPath) {
        this.currentPath = dirPath;
        List<String> item = new ArrayList();
        this.path = new ArrayList();
        this.mList = new ArrayList();
        File f = new File(this.currentPath);
        File[] files = f.listFiles();
        if (files == null) {
            this.currentPath = EXT_STORAGE;
            f = new File(this.currentPath);
            files = f.listFiles();
        }
        showLocation(R.string.file_dialog_location, this.currentPath);
        if (!this.currentPath.equals(EXT_STORAGE)) {
            item.add("ExtStorage");
            addItem("ExtStorage", R.drawable.file_dialog_folder);
            this.path.add(EXT_STORAGE);
        }
        if (!this.currentPath.equals(ROOT)) {
            item.add("../");
            addItem("../", R.drawable.file_dialog_folder);
            this.path.add(f.getParent());
            this.parentPath = f.getParent();
        }
        TreeMap<String, String> dirsMap = new TreeMap();
        TreeMap<String, String> dirsPathMap = new TreeMap();
        TreeMap<String, String> filesMap = new TreeMap();
        TreeMap<String, String> filesPathMap = new TreeMap();
        int length = files.length;
        for (int i = MODE_CREATE; i < length; i += MODE_OPEN) {
            File file = files[i];
            if (file.isDirectory()) {
                String dirName = file.getName();
                dirsMap.put(dirName, dirName);
                dirsPathMap.put(dirName, file.getPath());
            } else {
                String fileName = file.getName();
                String fileNameLwr = fileName.toLowerCase();
                if (this.formatFilter != null) {
                    boolean contains = false;
                    for (int i2 = MODE_CREATE; i2 < this.formatFilter.length; i2 += MODE_OPEN) {
                        if (fileNameLwr.endsWith(this.formatFilter[i2].toLowerCase())) {
                            contains = true;
                            break;
                        }
                    }
                    if (contains) {
                        filesMap.put(fileName, fileName);
                        filesPathMap.put(fileName, file.getPath());
                    }
                } else {
                    filesMap.put(fileName, fileName);
                    filesPathMap.put(fileName, file.getPath());
                }
            }
        }
        item.addAll(dirsMap.tailMap(BuildConfig.FLAVOR).values());
        List<String> list = item;
        list.addAll(filesMap.tailMap(BuildConfig.FLAVOR).values());
        this.path.addAll(dirsPathMap.tailMap(BuildConfig.FLAVOR).values());
        this.path.addAll(filesPathMap.tailMap(BuildConfig.FLAVOR).values());
        SimpleAdapter fileList = new SimpleAdapter(this, this.mList, R.layout.file_dialog_row, new String[]{ITEM_KEY, ITEM_IMAGE}, new int[]{R.id.fdrowtext, R.id.fdrowimage});
        for (String dir : dirsMap.tailMap(BuildConfig.FLAVOR).values()) {
            addItem(dir, R.drawable.file_dialog_folder);
        }
        for (String file2 : filesMap.tailMap(BuildConfig.FLAVOR).values()) {
            addItem(file2, R.drawable.file_dialog_file);
        }
        fileList.notifyDataSetChanged();
        setListAdapter(fileList);
    }

    private void addItem(String fileName, int imageId) {
        HashMap<String, Object> item = new HashMap();
        item.put(ITEM_KEY, fileName);
        item.put(ITEM_IMAGE, Integer.valueOf(imageId));
        this.mList.add(item);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        File file = new File((String) this.path.get(position));
        setSelectVisible(v);
        if (file.isDirectory()) {
            this.selectButton.setEnabled(false);
            if (file.canRead()) {
                this.lastPositions.put(this.currentPath, Integer.valueOf(position));
                getDir((String) this.path.get(position));
                if (this.canSelectDir) {
                    this.selectedFile = file;
                    v.setSelected(true);
                    this.selectButton.setEnabled(true);
                    return;
                }
                return;
            }
            new Builder(this).setIcon(R.drawable.error).setTitle("[" + file.getName() + "] " + getText(R.string.file_dialog_cant_read_folder)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();
            return;
        }
        this.selectedFile = file;
        v.setSelected(true);
        this.selectButton.setEnabled(true);
        showLocation(R.string.file_dialog_select, file.getPath());
        if (this.m_bOneClickSelect) {
            this.selectButton.performClick();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        this.selectButton.setEnabled(false);
        if (this.layoutCreate.getVisibility() == 0) {
            this.layoutCreate.setVisibility(8);
            this.layoutSelect.setVisibility(MODE_CREATE);
        } else if (this.currentPath.equals(EXT_STORAGE)) {
            return super.onKeyDown(keyCode, event);
        } else {
            getDir(this.parentPath);
        }
        return true;
    }

    private void setCreateVisible(View v) {
        this.layoutCreate.setVisibility(MODE_CREATE);
        this.layoutSelect.setVisibility(8);
        this.inputManager.hideSoftInputFromWindow(v.getWindowToken(), MODE_CREATE);
        this.selectButton.setEnabled(false);
    }

    private void setSelectVisible(View v) {
        if (!this.m_bOneClickSelect) {
            this.layoutCreate.setVisibility(8);
            this.layoutSelect.setVisibility(MODE_CREATE);
            this.inputManager.hideSoftInputFromWindow(v.getWindowToken(), MODE_CREATE);
            this.selectButton.setEnabled(false);
        }
    }
}
