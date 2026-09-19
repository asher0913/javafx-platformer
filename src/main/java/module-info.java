module io.github.asher0913.platformer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens io.github.asher0913.platformer to javafx.fxml;
    exports io.github.asher0913.platformer;
    exports io.github.asher0913.platformer.mvc.controller;
    opens io.github.asher0913.platformer.mvc.controller to javafx.fxml;
    exports io.github.asher0913.platformer.mvc.model;
    opens io.github.asher0913.platformer.mvc.model to javafx.fxml;
    exports io.github.asher0913.platformer.mvc.view;
    opens io.github.asher0913.platformer.mvc.view to javafx.fxml;
    exports io.github.asher0913.platformer.mvc.controller.utils;
    opens io.github.asher0913.platformer.mvc.controller.utils to javafx.fxml;
    exports io.github.asher0913.platformer.mvc.controller.state;
    opens io.github.asher0913.platformer.mvc.controller.state to javafx.fxml;
    exports io.github.asher0913.platformer.mvc.view.utils;
    opens io.github.asher0913.platformer.mvc.view.utils to javafx.fxml;
}