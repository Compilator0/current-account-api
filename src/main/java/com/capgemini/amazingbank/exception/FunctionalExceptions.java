package com.capgemini.amazingbank.exception;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
public class FunctionalExceptions {
    public enum Error {
        USER_NOT_FOUND("User not found.");

        private String label;

        Error(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum RelatedResource {
        USER("USER"),
        ACCOUNT("ACCOUNT"),
        TRANSACTION("TRANSACTION");

        private String label;

        RelatedResource(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
