package com.example.android.xmlparsingsax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    TextView result;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(TextView)findViewById(R.id.result);
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser saxParser = factory.newSAXParser();


            DefaultHandler handler = new DefaultHandler() {

                boolean name = false;

                boolean salary = false;


                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("name"))
                    {
                        name = true;
                    }
                    if (qName.equalsIgnoreCase("salary"))
                    {
                        salary = true;
                    }
                }
                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (name) {

                        result.setText(result.getText()+"\n\n Name : " + new String(ch, start, length));
                        name = false;
                    }
                    if (salary) {
                        result.setText(result.getText()+"\n Salary : " + new String(ch, start, length));
                        salary = false;
                    }
                }
            };

            InputStream is = getAssets().open("file.xml");
            saxParser.parse(is, handler);

        } catch (Exception e) {e.printStackTrace();}
    }
}
