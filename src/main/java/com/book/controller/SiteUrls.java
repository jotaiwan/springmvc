package com.book.controller;

public class SiteUrls {

    public static final String SITE_ROOT = "/";

    public static final class USER {
        public static final String ROOT = SITE_ROOT + "user";

        public static final String ALL = "/all";
        public static final String DELETE_USER = "/delete/{userId}";

        public static final String REGISTER_ACCOUNT = "/register/account";
        public static final String REGISTER_LOGIN = "/register/login";
        public static final String REGISTER_SAVE = "/register/save";

        public static final String ACCOUNT_EDIT = "/account/edit";
        public static final String ACCOUNT_UPDATE = "/account/update/{id}";

        public static final String LOGIN_EDIT = "/login/edit";
        public static final String LOGIN_UPDATE = "/login/update/{id}";

        public static final String DOCUMENT = "/document-{userId}";
        public static final String DOCUMENT_DOWNLOAD = "/download-document-{userId}-{docId}";
        public static final String DOCUMENT_DELETE = "/delete-document-{userId}-{docId}";

        // return jsp file
        public static final String MANAGER = ROOT + "/manager";
        public static final String REGISTRATION = ROOT + "/registration";
    }
}
