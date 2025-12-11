# UML Editor

## The documentation is in UML.pdf.

## Folder Structure
The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## File Tree
```
/UME/src
├── Buttons
|  ├── AssociationLineBtn.java
|  ├── ButtonAbstract.java
|  ├── CompositionLineBtn.java
|  ├── GeneralizationLineBtn.java
|  ├── OvalBtn.java
|  ├── RectBtn.java
|  └── SelectBtn.java
├── Factory
|  ├── AssociationLine.java
|  ├── CompositionLine.java
|  ├── GeneralizationLine.java
|  ├── LineInterface.java
|  ├── ObjectInterface.java
|  ├── OvalObject.java
|  └── RectObject.java
├── Menu
|  ├── CustomLabel.java
|  ├── EditMenu.java
|  ├── Group.java
|  ├── Menu.java
|  ├── MenuBar.java
|  ├── MenuItem.java
|  └── Ungroup.java
├── Mode
|  ├── AssociationLineMode.java
|  ├── CanvasMode.java
|  ├── CompositionLineMode.java
|  ├── GeneralizationLineMode.java
|  ├── OvalMode.java
|  ├── RectMode.java
|  └── SelectMode.java
├── Objects
|  ├── BasicObjects
|  |  ├── ObjectAbstract.java
|  |  ├── Oval.java
|  |  └── Rect.java
|  ├── CompositeObject.java
|  ├── Label.java
|  ├── Line
|  |  ├── Association.java
|  |  ├── Composition.java
|  |  ├── Generalization.java
|  |  └── LineAbstract.java
|  ├── Port.java
|  └── ShapeAbstract.java
└── View
   ├── Canvas.java
   ├── Frame.java
   ├── Main.java
   └── Panel.java
```

## Execution Flow
1. When click a button (e.g. RectBtn)
2. In RectBtn, it will set mode and create a new object.
   - `canvas.setMode(new RectMode(new RectObject()))`
3. RectMode() will create a Rect object.
4. Depend on which mode is clicked now, canvas will trigger the `currentMode.mousePressed(MouseEvent e)`
   - In this example, currentMode is `RectMode`, so  it will create a Rect object.