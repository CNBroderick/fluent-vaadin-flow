Fluent Vaadin Flow

**Inherits common Vaadin components --Broderick Labs**

**If have any question can contact me via : **

###### 	E-Mail: z@bkLab.org 

###### 	Twitter: [@China_Broderick]( https://twitter.com/China_Broderick  "twitter")

###### 	Source code: [Github Link](https://github.com/CNBroderick/fluent-vaadin-flow, "GitHub Link")



##### Need Java 15+ and Vaadin 19+, Better experience in China.



## Component Flow Factory

use component factory to configure Vaadin components as builder mode in one line. You can use Vaadin Component Name + Factory to use this.

```Java
public abstract class FlowFactory<C extends Component, E extends FlowFactory<C, E>> implements IFlowFactory<C>,
        AttachNotifierFactory<C, FlowFactory<C, E>>,
        DetachNotifierFactory<C, FlowFactory<C, E>> {
}

public interface IFlowFactory<C extends Component> extends Supplier<C> {
}
```





#### 1. Example For Button.

```java
Button deleteButton = new ButtonFactory().text("delete").lumoSmall().lumoIcon().lumoTertiaryInline()
                        .icon(VaadinIcon.TRASH).clickListener(e -> remove(entity)).get();
Button menuButton = new ButtonFactory().icon(VaadinIcon.ELLIPSIS_DOTS_H.create())
                .lumoIcon().lumoSmall().lumoTertiaryInline().get();
```



#### 2. Example For ComboBox\<T> factory

```java
ComboBox<Building> buildingComboBox = new ComboBoxFactory<Building>()
                .allowCustomValue(false).clearButtonVisible(false).itemLabelGenerator(Building::getName)
                .peek(r -> parameterMap.put("buildingId", () -> Optional.ofNullable(r.getValue()).map(Building::getId).orElse(-999)))
                .valueChangeListener(e -> searchButton.clickInClient())
                .valueChangeListener(e -> reloadFloors())
                .className(CLASS_NAME + "__building-combo-box").width("15em").lumoSmall()
                .pageSize(999).get()
```



#### 3. Example For Date Picker 

```java
DatePicker datePicker = new DatePickerFactory().lumoSmall()
                .min(LocalDate.now().minusYears(1))
                .max(LocalDate.now().plusYears(1))
                .clearButtonVisible(true)
                .widthFull().get()
```

> Warning: Date Picker, Time picker, Date Time Picker's Factory default use Chinese locale.

```java
  get().setLocale(Locale.CHINA);
  get().setI18n(ChineseDatePickerI18n.getInstance());
```

> if you not accept can use 

```java
DatePicker datePicker1 = new DatePickerFactory(new DatePicker(), Locale.ENGLISH).get();

DatePicker datePicker2 = new DatePickerFactory(
    new DatePicker(), (Locale) null, (DatePicker.DatePickerI18n) null).get();
```



#### 4. Example For Text Field

```java 
TextField textField = new TextFieldFactory().label("label 1").width("80vh")
    .suffixComponent(new Text("Km/s")).lumoSmall().get();
```



#### 5. Support Components

 - AccordionFactory
 - AnchorFactory
 - AppLayoutFactory
 - ButtonFactory
 - CheckboxFactory
 - ComboBoxFactory
 - ComponentFactory
 - ContextMenuFactory
 - CustomFieldFactory
 - DatePickerFactory
 - DateTimePickerFactory
 - DetailsFactory
 - DialogFactory
 - DivFactory
 - EmailFieldFactory
 - FlexLayoutFactory
 - FormLayoutFactory
 - GenerateHtmlContainerFactory
 - GridFactory
 - H1Factory
 - H2Factory
 - H3Factory
 - H4Factory
 - H5Factory
 - H6Factory
 - HorizontalLayoutFactory
 - IconFactory
 - ImageFactory
 - ListBoxFactory
 - MenuBarFactory
 - NotificationFactory
 - NumberFieldFactory
 - PasswordFieldFactory
 - ProgressBarFactory
 - RadioButtonGroupFactory
 - SelectFactory
 - SpanFactory
 - SplitLayoutFactory
 - TabsFactory
 - TextAreaFactory
 - TextFieldFactory
 - TimePickerFactory
 - TreeGridFactory
 - UploadFactory
 - VerticalLayoutFactory



#### 6. Support Base Component 

- AbstractFieldFactory
- AbstractNumberFieldFactory
- AbstractSinglePropertyFieldFactory
- AttachNotifierFactory
- BlurNotifierFactory
- ClickNotifierFactory
- CompositionNotifierFactory
- ContextMenuBaseFactory
- DetachNotifierFactory
- FlexComponentFactory
- FocusableFactory
- FocusNotifierFactory
- GeneratedVaadinButtonFactory
- GeneratedVaadinCheckboxFactory
- GeneratedVaadinComboBoxFactory
- GeneratedVaadinContextMenuFactory
- GeneratedVaadinDatePickerFactory
- GeneratedVaadinEmailFieldFactory
- GeneratedVaadinFormLayoutFactory
- GeneratedVaadinNotificationFactory
- GeneratedVaadinNumberFieldFactory
GeneratedVaadinPasswordFieldFactory.jav
- GeneratedVaadinProgressBarFactory
- GeneratedVaadinRadioGroupFactory
- GeneratedVaadinSelectFactory
- GeneratedVaadinSplitLayoutFactory
- GeneratedVaadinTabsFactory
- GeneratedVaadinTextAreaFactory
- GeneratedVaadinTextFieldFactory
- GeneratedVaadinTimePickerFactory
- GeneratedVaadinUploadFactory
- HasAutocapitalizeFactory
- HasAutocompleteFactory
- HasAutocorrectFactory
- HasComponentsFactory
- HasDataGeneratorsFactory
- HasDataProviderFactory
- HasDataViewFactory
- HasEnabledFactory
- HasFilterableDataProviderFactory
- HasHierarchicalDataProviderFactory
- HasItemsAndComponentsFactory
- HasItemsFactory
- HasLazyDataViewFactory
- HasListDataViewFactory
- HasMenuItemsFactory
- HasOrderedComponentsFactory
- HasPrefixAndSuffixFactory
- HasSizeFactory
- HasStyleFactory
- HasTextFactory
- HasThemeFactory
- HasValidationFactory
- HasValueAndElementFactory
- HasValueChangeModeFactory
- HasValueFactory
- HtmlComponentFactory
- HtmlContainerFactory
- InputNotifierFactory
- KeyNotifierFactory
- ListBoxBaseFactory
- MenuItemBaseFactory
- RouterLayoutFactory
- SingleSelectFactory
- SortEventSortNotifierFactory
- SubMenuBaseFactory
- ThemableLayoutFactory



## Fluent Button

![image-20200808093400176](https://bklab.oss.bbkki.com/img/20200808093407.png)

> From left to right

#### 1. Normal Button

```java
 Button normal = new FluentButton(VaadinIcon.LIST, "普通按钮");
```



#### 2. Primary Button

```java
 Button primary = new FluentButton(VaadinIcon.CHECK_CIRCLE, "主要按钮").primary();
```



#### 3. Primary Error Button

```java
Button primaryError = new FluentButton(VaadinIcon.CHECK_CIRCLE, "主要按钮").primary().error();
```



#### 4. Error Button

```java
Button error = new FluentButton(VaadinIcon.INFO_CIRCLE, "错误按钮").error();
```



#### 5. Error Dashed Button

```java
Button errorDashed = new FluentButton(VaadinIcon.INFO_CIRCLE, "错误按钮").error().dashed();
```



#### 6. Dashed Button

```java
Button errorDashed = new FluentButton(VaadinIcon.DASHBOARD, "虚线按钮").dashed();
```



#### 7. Link Button

```java
Button link = new FluentButton(VaadinIcon.LINK, "连接按钮").link();
```



#### 8. Inside static creator

```java
    public static FluentButton saveButton() {
        return new FluentButton(VaadinIcon.CHECK_CIRCLE_O, "保存").primary();
    }

    public static FluentButton updateButton() {
        return new FluentButton(VaadinIcon.EDIT, "修改").primary();
    }

    public static FluentButton closeButton() {
        return new FluentButton(VaadinIcon.CLOSE_SMALL, "关闭");
    }

    public static FluentButton cancelButton() {
        return new FluentButton(VaadinIcon.CLOSE_SMALL, "取消");
    }

    public static FluentButton errorButton() {
        return new FluentButton(VaadinIcon.EXCLAMATION_CIRCLE_O, "错误");
    }
```



#### 8. Also can as Button Factory

```java
    Button save = FluentButton.saveButton().primary().asFactory().clickListener(e -> {
                /* ... */
    }).visible(!updateMode).text("保存并继续").get();
```



## Dialog



#### 1. Ask Dialog

![image-20200808110410344](https://bklab.oss.bbkki.com/img/20200808110410.png)

```java
    new AskDialog("是否退出登录？", y -> logout()).build().open();
```



#### 2. Message Dialog

##### 2.1 normal  (with title)

![image-20200808112359152](https://bklab.oss.bbkki.com/img/20200808112359.png)

```java
    new MessageDialog().message("这是一个则消息").header("提示").build().open();
```



##### 2.2 normal (without title)

![image-20200808112607391](https://bklab.oss.bbkki.com/img/20200808112607.png)

```java 
    new MessageDialog().message("这是一个则消息").build().open();
```



##### 2.3 success (with title)

![image-20200808112814765](https://bklab.oss.bbkki.com/img/20200808112814.png)

```java
   new MessageDialog().message("这是一个则成功消息").header("成功").forSuccess().build().open();
```



##### 2.4 Success (without title)

![image-20200808112904429](https://bklab.oss.bbkki.com/img/20200808112904.png)



```java
   new MessageDialog().message("这是一个则成功消息").forSuccess().build().open();
```



##### 2.5 Warning (with title)

![image-20200808112954059](https://bklab.oss.bbkki.com/img/20200808112954.png)

```java
    new MessageDialog().message("这是一个则警告消息").header("警告").forWarning().build().open();
```





##### 2.5 Warning (without title)

![image-20200808113141489](https://bklab.oss.bbkki.com/img/20200808113141.png)

```java
    new MessageDialog().message("这是一个则警告消息").forWarning().build().open();
```



### 3. Modal Dialog

![image-20200808113710932](https://bklab.oss.bbkki.com/img/20200808113711.png)

```java
    new ModalDialog().title("对话框")
            .content(new Div(new Span("Some contents...")))
            .content(new Div(new Span("Some contents...")))
            .content(new Div(new Span("Some contents...")))
            .addCloseButton()
            .addSaveButton(buttonClickEvent -> {
            })
            .width("521px")
            .open();
```



### 4. Download Dialog

![image-20200808114202451](https://bklab.oss.bbkki.com/img/20200808114202.png)

```java
new DownloadDialog("Room Export finished, please download.", 
                   new StreamResource(name, () -> stream)).build().open();
```



## Pagination

### 1. Normal mode

![image-20200808114626389](https://bklab.oss.bbkki.com/img/20200808114626.png)

```java
    new Pagination().totalData(1000).limit(10).init().build();
```



### 2. Tiny mode

![image-20200808114716224](https://bklab.oss.bbkki.com/img/20200808114716.png)

```java
    new Pagination().totalData(1000).limit(10).tinyMode().init().build();
```



### 3. Custom four component layout

#### 3.1 Middle layout

![image-20200808114918514](https://bklab.oss.bbkki.com/img/20200808114918.png)

```java
    new Pagination().customLayout(new MiddleCustomPaginationLayout());
```

#### 3.2 Custom layout

> implements interface:

```java
public interface ICustomPaginationLayout {

    /**
     * 自定义翻页组件布局
     *
     * @param toolBar       tool bar 左中右布局
     * @param totalLabel    全部数据文本
     * @param pageContainer 翻页按钮
     * @param jumpField     跳转输入框
     * @param pageSize      单页数量选择框
     */
    void apply(ToolBar toolBar,
               PaginationTotalLabel totalLabel,
               Div pageContainer,
               PaginationJumpField jumpField,
               PaginationPageSize pageSize
    );
}

```



> Such as middle:

```java

public class MiddleCustomPaginationLayout implements ICustomPaginationLayout {
    @Override
    public void apply(ToolBar toolBar, PaginationTotalLabel totalLabel, Div pageContainer, PaginationJumpField jumpField, PaginationPageSize pageSize) {
        toolBar.middle(totalLabel, pageContainer, pageSize);
    }
}
```

> with lambada:

```java
ICustomPaginationLayout customPaginationLayout = 
                (toolBar, totalLabel, pageContainer, jumpField, pageSize) 
                        -> toolBar.middle(totalLabel, pageContainer, pageSize)
```



## Chart Js 

![image-20200809075522222](https://bklab.oss.bbkki.com/img/20200809075529.png)

[Official Link](https://www.chartjs.org)

[China docs cdn](https://bbkki.com/docs/chartjs)

### 1. Direct to use

```java
	new ChartJs("{}");
```



### 2. Use custom class

#### example: 

```java
	VerticalBarChart chart = new VerticalBarChart()
        .withSize("99%", "300px")
        .setColors(colors).setNumberSuffix("㎡")
        .addData(new LinkedHashMap<String, Double>())
        .build()
```



#### base class

```
package org.bklab.flow.chartjs;

public abstract class AbstractChartJs<T extends AbstractChartJs<T>> extends VerticalLayout {
	
//	...
	
	protected abstract Chart createChart();
	
	public T build() {
        checkColors();
        this.chartJs = new ChartJs(createChartJson());
        add(chartJs);
        return (T) this;
    } 
    
//    ...
}
```



#### custom class

```java
package org.bklab.ui.chart;

import be.ceau.chart.Chart;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import org.bklab.flow.chartjs.AbstractChartJs;

public class VerticalBarChart extends AbstractChartJs<VerticalBarChart> {

    @Override
    protected Chart createChart() {
        return new Chart() {
            @Override
            public String getType() {
                return "line";
            }

            @Override
            public String toJson() {
                return createConfig().buildJson().toJson();
            }

            @Override
            public boolean isDrawable() {
                return false;
            }
        };
    }

    public BarChartConfig createConfig() {
        BarChartConfig barConfig = new BarChartConfig();

        BarDataset dataset = new BarDataset().backgroundColor(getColorArray());
        data.forEach((k, v) -> dataset.addLabeledData(k, v.doubleValue()));
        double min = data.values().stream().mapToDouble(Number::doubleValue).min().orElse(0);
        min = min > 0 ? 0 : min;
        LinearScale linearScale = new LinearScale().ticks().min(min).callback(createFormatNumberFunction().getFunction()).and();

        barConfig.
                data()
                .labels(data.keySet().toArray(new String[]{})).addDataset(dataset).and()
                .options()
                .responsive(true).tooltips().enabled(true).callbacks().label(createFormatNumberJs().getFunction()).and().and()
                .hover().mode(InteractionMode.INDEX).intersect(true).animationDuration(400).and()
                .title().display(true).text(title).and()
                .legend().display(false).and()
                .scales().add(Axis.Y, linearScale)
                .and()
                .maintainAspectRatio(false)
                .done();

        return barConfig;
    }
}
```





## Button Selector

![image-20200808115802468](https://bklab.oss.bbkki.com/img/20200808115802.png)

```java
    Consumer<String> consumer = s -> new NotificationFactory(s)
        .lumoSuccess().positionTopEnd().duration(3000).open();

    ButtonSelector buttonSelector = new ButtonSelector()
            .add("第1个", e -> consumer.accept("第1个"))
            .add("第2个", e -> consumer.accept("第2个"))
            .add("第3个", e -> consumer.accept("第3个"))
            .add("第4个", e -> consumer.accept("第4个"))
            .add("第5个", e -> consumer.accept("第5个"))
            .add("第6个", e -> consumer.accept("第6个"))
            .activeFirst();
```



## Empty Layout

### 1.  normal

![image-20200808120150265](https://bklab.oss.bbkki.com/img/20200808120150.png)



```java
   new EmptyLayout();
```



### 2. custom message

![image-20200808120234304](https://bklab.oss.bbkki.com/img/20200808120234.png)

``` java
     new EmptyLayout("未上传任何图片");
```



## Badge Tag

![image-20200808134602486](https://bklab.oss.bbkki.com/img/20200808134602.png)



```java
    new BadgeTag("febs-tag-green", BadgeTagStyle.GREEN);
    new BadgeTag("febs-tag-green", BadgeTagStyle.BG_GREEN);
    // text, background, border
    new BadgeTag("febs-tag-green", "white", "black");
```



## Drag Select Layout

![image-20200808135007505](https://bklab.oss.bbkki.com/img/20200808135007.png)

```java
    public void create() {
        DragSelectLayout<Room> dragSelectLayout = new DragSelectLayout<Room>()
                .whenEmpty("请拖动左侧房间到此处").componentRender(roomRender());
        dragSelectLayout.containerHeight("40vh", "320px", "70vh");
        dragSelectLayout.getSelectedFooter().right(new DivFactory(selectCount, selectArea).displayGrid().textAlign("end").get());
        dragSelectLayout.addValueChangeListener(e -> {
            double sum = e.getValue().stream().mapToDouble(Room::getArea).sum();
            int size = e.getValue().size();
            selectCount.setText(size + " 房间");
            selectArea.setText(new DigitalFormatter(sum).toAreaWord());
        });
        dragSelectLayout.getCandidateHeader().right(floorComboBox, keywordField);
        dragSelectLayout.items(rooms);
    }

    private Function<Room, Div> roomRender() {
        return room -> new DivFactory(
                new DivFactory().text(room.getRoomNo()).margin(0).textAlign("start").fontSize("var(--lumo-font-size-l)").get(),
                new DivFactory().text(String.format("%.2f ㎡", room.getArea())).margin(0).textAlign("start").fontSize("var(--lumo-font-size-s)").get()
        ).padding("0.5em").border().margin("0.5em").minWidth("70px").maxHeight("50px").get();
    }
```



## Title Layout

![image-20200808135631243](https://bklab.oss.bbkki.com/img/20200808135631.png)

```java
    new TitleLayout().content(new Image("https://bklab.oss.bbkki.com/img/20200808135631.png"))
        .title("Title Layout Header")/*.left().middle()*/
        .right(
                createUpdateButton(),
                createDeleteButton(),
                createAddButton()
        );
```



## Tool Bar

![image-20200808140040653](https://bklab.oss.bbkki.com/img/20200808140040.png)

Has left, middle, right components in one line.

```java
 new ToolBar()
     .left(new TitleLabel("tool bar left"))
     .middle(new TitleLabel("tool bar middle"))
     .right(new TitleLabel("tool bar right 1"), new TitleLabel("tool bar right 2"));
```



## Main App Layout

![image-20200808140407758](https://bklab.oss.bbkki.com/img/20200808140407.png)

![image-20200808140421195](https://bklab.oss.bbkki.com/img/20200808140421.png)



```java

@Route("")
@PageTitle("布克约森实验室")
public class View extends MainAppLayout {

    public View() {
        appBar.lightTheme();
        appBar.logout(e -> UI.getCurrent().navigate(LoginView.class)).avatarName("Broderick Labs");
        naviDrawer.disableSwap();
        appBar.getContextIcon().setVisible(true);
        setWidthFull();
    }

    @Override
    public void buildNaviMenu(NaviMenu naviMenu) {
        naviMenu.addNaviItem(VaadinIcon.VAADIN_H, "组件库", ComponentDemoView.class);
        naviMenu.addNaviItem(VaadinIcon.DATABASE, "暂无数据", EmptyView.class);
        naviMenu.addNaviItem(VaadinIcon.PACKAGE, "翻页工具", PaginationView.class);
    }

    @Override
    public void buildUserIcon(Image image, ContextMenu contextMenu) {
        contextMenu.addItem("登录", e -> UI.getCurrent().navigate(LoginView.class));
    }

    @Override
    protected Class<? extends Component> defaultNavigationTarget() {
        return ComponentDemoView.class;
    }
}


@Route(value = "empty", layout = View.class)
@PageTitle("empty --Broderick Labs")
public class EmptyView extends Div {

    public EmptyView() {
        add(new EmptyLayout("暂无数据"));
        setSizeFull();
    }
}

```



## Fluent Crud View

```java

@PageTitle(PageTitleDefinition.SPACE_ADMIN)
@Route(value = RouteDefinition.SPACE_ADMIN, layout = MainView.class)
public class ExampleAdminView extends FluentCrudView<Room, Grid<Example>> {
    private final MultiSelectBox<Floor> floorComboBox;
    private final ComboBox<Building> buildingComboBox;

    {
        floorComboBox = new MultiSelectBox<>();
        floorComboBox.itemLabelGenerator(Floor::getFloorName);
        floorComboBox.getMultiSelectListBox().addSelectionListener(e -> reloadGridData());
        parameterMap.put("floors", () -> {
            Set<Floor> values = floorComboBox.getValues();
            return values == null || values.isEmpty() ? null : values;
        });
    }
    
    public ExampleAdminView() {
        addMenuColumn(new ExampleMenuBuilder());
        initGrid();
        initConditions();
        pagination.onePageSize(10);
        searchButton.setVisible(false);
        setSameEntityBiPredicate((a, b) -> a != null && b != null && a.getId() == b.getId());
    } 
    
    private void initGrid() {
        grid.getColumnByKey("floorName").setHeader(floorComboBox);
    }
    
    private void initConditions() {
        header.left(buildingComboBox).right(addKeywordField());
    }

    @Override
    public Grid<Example> createGrid() {
        return new ExampleGrid();
    }

    @Override
    public Collection<Example> queryEntities(Map<String, Object> parameters) {
        return new ArrayList();
    }
 
}


public class ExampleMenuBuilder implements IFluentMenuBuilder<Room, RoomGrid> {

    @Override
    public void build(FluentCrudView<Example, Grid<Example>> fluentCrudView, ContextMenu contextMenu, Example example) {
          FluentMenuItem.create(VaadinIcon.EDIT, "edit floor")
              .add(contextMenu, menuItemClickEvent ->{});
          FluentMenuItem.forDelete("delete room")
              .add(contextMenu, menuItemClickEvent ->{});
    }
}
```





## More Components In This & More Components Adding

This README.md still improving, More unlisted components are still being added. 



## Development instructions

JavaScript modules can either be published as an NPM package or be kept as local 
files in your project. The local JavaScript modules should be put in 
`src/main/resources/META-INF/frontend` so that they are automatically found and 
used in the using application.

If the modules are published then the package should be noted in the component 
using the `@NpmPackage` annotation in addition to using `@JsModule` annotation.


Starting the test/demo server:
1. Run `mvn jetty:run`.
2. Open http://localhost:8080 in the browser.

## Publishing to Vaadin Directory

You can create the zip package needed for [Vaadin Directory](https://vaadin.com/directory/) using
```
mvn versions:set -DnewVersion=1.0.0 # You cannot publish snapshot versions 
mvn install -Pdirectory
```

The package is created as `target/fluent-vaadin-flow-*.*.*.zip`

For more information or to upload the package, visit https://vaadin.com/directory/my-components?uploadNewComponent
