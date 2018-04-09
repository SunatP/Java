# Solve For Error on Android
-----------------------------------------------------------------------------------------------
After examining the logcat, the issue seems to be:
Caused by: android.content.res.Resources$NotFoundException: Resource is not a Drawable (color or path): TypedValue{t=0x1/d=0x7f02006a a=-1 r=0x7f02006a}

Usually, there are two possible explanations for this error:

    the support.design library and the appCompact library versions do not match;
    there is an error while creating the app theme, usually a wrong textPrimaryColor declaration.

In order to solve the problem, I suggest you to check these things, in order:

    in the project's "build.gradle", check that all dependencies are the last possible version;
    in your "colors.xml", check that all the colors are correctly declared;
    in "styles.xml", check that all the colors are the same as the ones declared in the previous file;
    in your "manifest" file, check if the theme in use is one of the themes declared in the previous file.

If you still can't solve the problem, please provide us some more informations, in order to help you solve this issue. We shall need:

    the project's build.gradle
    colors.xml, styles.xml, manifest
    the exact lines that are throwing the exception.

-----------------------------------------------------------------------------------------------
# Change Language On Android Studio
Configuration config = new Configuration();
config.locale = Language Such Thai -> Locale.TH;
getResources().updateConfiguration(config, null);

now -> config.locale =  new Locale("th")

Example 
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button butonEn = (Button)findViewById(R.id.buttonEn);
        butonEn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Configuration config = new Configuration();
                config.locale = Locale.ENGLISH;
                getResources().updateConfiguration(config, null);

                onCreate(null);
            }
        });
        
        
        Button butonIt = (Button)findViewById(R.id.butonIt);
        butonIt.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Configuration config = new Configuration();
                config.locale = Locale.ITALIAN;
                getResources().updateConfiguration(config, null);

                onCreate(null);
            }
        });
        
        
        Button butonTh = (Button)findViewById(R.id.butonTh);
        butonTh.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Configuration config = new Configuration();
                config.locale = new Locale("th");
                getResources().updateConfiguration(config, null);

                onCreate(null);
            }
        });
    }
}