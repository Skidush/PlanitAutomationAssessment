package com.planit.enums;

public class HeaderButtons {
    public enum HEADER_LINKS {
        HOME ("home"),
        SHOP ("shop"),
        CONTACT ("contact"),
        CART ("cart");

        private final String _href;
        HEADER_LINKS(String href) {
            _href = href;
        }

        public String toString() {
            return _href;
        }
    }

    public enum HEADER_BUTTONS {
        LOGIN ("login");

        private final String _name;
        HEADER_BUTTONS(String name) {
            _name = name;
        }

        public String toString() {
            return _name;
        }
    }
}
