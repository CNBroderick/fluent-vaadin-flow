package org.bklab;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.crud.FluentCrudView;
import org.bklab.crud.IFluentMenuBuilder;
import org.bklab.flow.components.menu.FluentMenuItem;
import org.bklab.flow.factory.GridFactory;
import org.bklab.flow.factory.NotificationFactory;
import org.bklab.flow.factory.SpanFactory;
import org.bklab.flow.util.css.FluentColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@Route(value = "color", layout = View.class)
@PageTitle("crud --Broderick Labs")
public class CrudView extends FluentCrudView<FluentColor, Grid<FluentColor>> {

    public CrudView() {
        searchButton.setVisible(false);
        addMenuColumn(new FluentColorMenuBuilder());
    }

    @Override
    public Grid<FluentColor> createGrid() {
        return new GridFactory<>(FluentColor.class, true)
                .peek(grid -> grid.addComponentColumn(c -> new SpanFactory("☻ ₡ ■").height("1em").width("1em")
                        .style("color", c.rgb()).style("font-size", "1em").get()
                ).setHeader("#").setKey("span").setWidth("2em"))
                .sizeFull().columnAutoWidth().columnAlignCenter().get();
    }

    @Override
    public Collection<FluentColor> queryEntities(Map<String, Object> parameters) {
        return Arrays.asList(FluentColor.values());
    }

    private static class FluentColorMenuBuilder implements IFluentMenuBuilder<FluentColor, Grid<FluentColor>> {

        @Override
        public void build(FluentCrudView<FluentColor, Grid<FluentColor>> fluentCrudView, ContextMenu contextMenu, FluentColor entity) {
            FluentMenuItem.forAdd("detail").add(contextMenu, event -> new NotificationFactory(event.getSource().getText()).positionTopEnd().duration(3).lumoSuccess().open());
            FluentMenuItem.forAdd("broderick").add(contextMenu, event -> new NotificationFactory(event.getSource().getText()).positionTopEnd().duration(3).lumoSuccess().open());
            FluentMenuItem.forAdd("color").add(contextMenu, event -> new NotificationFactory(event.getSource().getText()).positionTopEnd().duration(3).lumoSuccess().open());
            FluentMenuItem.forAdd("fluent").add(contextMenu, event -> new NotificationFactory(event.getSource().getText()).positionTopEnd().duration(3).lumoSuccess().open());
        }
    }
}
