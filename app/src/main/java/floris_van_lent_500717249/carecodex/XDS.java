package floris_van_lent_500717249.carecodex;

import android.content.ContentValues;

import floris_van_lent_500717249.carecodex.database.XDS_Table;

/**
 * TODO
 * Data niet direct uit XDS laten zien
 * Encryption & decryption toevoegen
 * MainActivity met zorgstappen volstoppen
 * Op basis van zorgstap de volgorde van samenvoegen verwerken
 * Op basis van data-type een apart view vakje bedenken
 *
 * Created by Floris on 22-03-2017.
 */

public class XDS {
    private String id;
    private String type;
    private String value;

    public XDS() {
    }

    public XDS(String id, String type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String person) {
        this.value = person;
    }

    public ContentValues toValues() {
        ContentValues values = new ContentValues(3);

        values.put(XDS_Table.COLUMN_ID, id);
        values.put(XDS_Table.COLUMN_TYPE, type);
        values.put(XDS_Table.COLUMN_VALUE, value);

        return values;
    }
}
