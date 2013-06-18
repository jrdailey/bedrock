/**
 * Copyright 2013, CITYTECH, Inc.
 * All rights reserved - Do Not Redistribute
 * Confidential and Proprietary
 */
package com.citytechinc.cq.library.content.link;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A link represents the attributes that compose a URL with additional title and target properties to encapsulate the
 * typical attributes of an HTML anchor tag.
 */
public interface Link extends Serializable {

    /**
     * @return extension
     */
    String getExtension();

    /**
     * @return href (path with extension)
     */
    String getHref();

    /**
     * @return path
     */
    String getPath();

    /**
     * @return property map
     */
    Map<String, String> getProperties();

    /**
     * @return query string starting with '?' or empty string if no parameters present
     */
    String getQueryString();

    /**
     * @return list of selector values
     */
    List<String> getSelectors();

    /**
     * @return link target
     */
    String getTarget();

    /**
     * @return link title
     */
    String getTitle();

    /**
     * @return true if href is null or empty
     */
    boolean isEmpty();

    /**
     * @return if href is to an external URL
     */
    boolean isExternal();
}
