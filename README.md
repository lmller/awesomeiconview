# AwesomeIconView
AwesomeIconView is an Android View that displays a FontAwesome icon!

### What is Font Awesome?
Font Awesome is the "The iconic font and CSS toolkit", which basically means you can use text to display icons. No images needed!
For more info visit http://fortawesome.github.io/Font-Awesome/

### Why a library for Android?
Many UI designers originally come from web development. They are therefore used to working with Font Awesome. 
If a designer tells you to use e.g. the "Android" font awesome icon, you can now do so without having to export a png.
I once ran into this problem at a project, so I created this library back in 2013. Maybe there is already a more sophisticated one, but I decided to share this anyway.

### How to use it
Drop the aar from the libraries folder into your app's own library folder.

Add the following to your build.gradle:
```
repositories {
    flatDir {
        dirs 'libs'
    }
}
...
dependencies {
     compile (name: 'awesomeiconview-0.1.2', ext: 'aar')
   ...
}
```

To use it, invoke it from your layout xml:
```
  <com.github.lmller.awesomeiconview.AwesomeIconView
        android:id="@+id/icon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="5dp"
        app:iconSize="30dp"
        app:iconColor="#ff6666"
        app:fontAwesomeIcon="Chrome"
        />
```

Or from code:
```
((AwesomeIconView)findViewById(R.id.icon)).setIcon(AwesomeIconView.FontAwesomeIcon.ANDROID);
```

Check the Font Awesome Cheat Sheet at https://fortawesome.github.io/Font-Awesome/cheatsheet/ to pick the icon you want.

**Note:** This library is currently in early alpha, os it only supports around 20 different icons. ~Will be updated ASAP.~


