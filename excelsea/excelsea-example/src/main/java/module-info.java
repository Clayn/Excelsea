module de.clayntech.excelsea.example {
    requires transitive de.clayntech.excelsea.fw;
    requires javafx.graphics;
    requires javafx.controls;
    requires de.clayntech.excelsea.common;
    requires de.clayntech.excelsea.fx;
    exports de.clayntech.excelsea.example.fx;
    requires org.slf4j;
    requires de.clayntech.excelsea.log;
}