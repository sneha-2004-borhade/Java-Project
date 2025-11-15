

package com.home;

import javafx.scene.image.Image;

public class Product {

    private Image image;
    private String title;
    private int days;

    // ✅ हे नवीन field – हे दर्शवतं की हा प्रॉडक्ट Firebase ला पाठवला आहे का
    private boolean syncedToFirebase = false;

    // ✅ Constructor with all details
    public Product(Image image, String title, int days) {
        this.image = image;
        this.title = title;
        this.days = days;
    }

    // ✅ Alternate constructor (default days = 0)
    public Product(Image image, String title) {
        this(image, title, 0);
    }

    public Image getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getDays() {
        return days;
    }

    // ✅ Getter: Firebase ला synced आहे का?
    public boolean isSyncedToFirebase() {
        return syncedToFirebase;
    }

    // ✅ Setter: Firebase ला synced झाल्यावर true कर
    public void setSyncedToFirebase(boolean syncedToFirebase) {
        this.syncedToFirebase = syncedToFirebase;
    }
}
