package org.bklab.crud.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.function.ValueProvider;
import org.bklab.crud.FluentCrudView;
import org.bklab.flow.factory.ButtonFactory;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ICrudViewMenuColumnSupporter<T, G extends Grid<T>, C extends FluentCrudView<T, G>> {


    default C addMenuColumn(IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return this.addMenuColumn(() -> new ButtonFactory().icon(VaadinIcon.ELLIPSIS_DOTS_H.create())
                .lumoIcon().lumoSmall().lumoTertiaryInline().get(), menuEntityBiConsumer);
    }

    default C addEditIconMenuColumn(IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return this.addMenuColumn(() -> new ButtonFactory().icon(VaadinIcon.EDIT.create())
                .lumoIcon().lumoSmall().lumoTertiaryInline().get(), menuEntityBiConsumer);
    }

    default C addMenuColumn(Supplier<Button> buttonSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return addMenuColumn(entity -> buttonSupplier.get(), menuEntityBiConsumer);
    }

    default C addMenuColumn(Function<T, Button> buttonSupplier, IFluentMenuBuilder<T, G> menuEntityBiConsumer) {
        return addMenuColumn(entity -> {
            Button button = buttonSupplier.apply(entity);
            ContextMenu contextMenu = new ContextMenu(button);
            contextMenu.addOpenedChangeListener(e -> {
                if (e.isOpened()) {
                    if (contextMenu.getItems().isEmpty()) {
                        menuEntityBiConsumer.safeBuild(getCrudView(), contextMenu, entity);
                        contextMenu.setVisible(true);
                    }
                    getCrudView().getGrid().select(entity);
                }
            });
            FluentCrudMenuButton<T, G> crudMenuButton = new FluentCrudMenuButton<>(getCrudView(), entity, button, contextMenu, menuEntityBiConsumer);
            getMenuButtons().add(crudMenuButton);
            contextMenu.setOpenOnClick(true);
            menuEntityBiConsumer.safeBuild(getCrudView(), contextMenu, entity);
            return button;
        });
    }

    default <V extends Component> C addMenuColumn(ValueProvider<T, V> componentProvider) {
        Grid.Column<T> column = getCrudView().getGrid().addComponentColumn(componentProvider).setHeader("操作")
                .setKey("menuColumn").setSortable(false).setWidth("5em").setTextAlign(ColumnTextAlign.CENTER);
        return getCrudView();
    }

    List<FluentCrudMenuButton<T, G>> getMenuButtons();

    C getCrudView();
}
