package com.soccer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.soccer.main.R;

import android.content.Context;

public class CSVUtil {

	private Context context;
	
	public CSVUtil(Context context) {
		 this.context = context;
	}
	
	public String getName() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.firstnames)));
		String fname = reader.
	}
}

BufferedReader reader = new BufferedReader(new InputStreamReader(is));
try {
    String line;
    while ((line = reader.readLine()) != null) {
         String[] RowData = line.split(",");
         date = RowData[0];
         value = RowData[1];
        // do something with "data" and "value"
    }
}
catch (IOException ex) {
    // handle exception
}
finally {
    try {
        is.close();
    }
    catch (IOException e) {
        // handle exception
    }
}