# Kotlin ProgressTextView
The project is a simple custom view from an extended View.
<br><br>
## Usage
- In XML Layout
```xml
<vic.sample.progresstextview.custom.ProgressTextView
    android:id="@+id/progressTextView"
    android:layout_width="match_parent"
    android:layout_height="64dp"
    android:layout_margin="36dp"
    android:background="@drawable/bk"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:invertedTextColor="@color/white"
    app:originalTextColor="@color/black"
    app:progress="50"
    app:text="Hello World!"
    app:textSize="24sp" />
```
- All customizable attributes:
```xml
<declare-styleable name="ProgressTextView">
    <attr name="text" format="string" />
    <attr name="textSize" format="dimension" />
    <attr name="textTypeface" format="integer" />
    <attr name="originalTextColor" format="color" />
    <attr name="invertedTextColor" format="color" />
    <attr name="maxProgress" format="integer" />
    <attr name="minProgress" format="integer" />
    <attr name="progress" format="integer" />
    <attr name="progressIncrease" format="boolean" />
</declare-styleable>
```
## The UI Flow
<br>
<img src="https://github.com/KeithWang/Kotlin-ProgressTextView/blob/master/pic/flow.gif?raw=true" height="530" width="300" />
