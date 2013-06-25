/**
 * Copyright 2013, CITYTECH, Inc.
 * All rights reserved - Do Not Redistribute
 * Confidential and Proprietary
 */
package com.citytechinc.cq.library.content.node.predicates;

import com.citytechinc.cq.library.content.node.ComponentNode;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public final class ComponentNodePropertyValuePredicate<T> implements Predicate<ComponentNode> {

    private static final Logger LOG = LoggerFactory.getLogger(ComponentNodePropertyValuePredicate.class);

    private final String propertyName;

    private final T propertyValue;

    public ComponentNodePropertyValuePredicate(final String propertyName, final T propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = checkNotNull(propertyValue);
    }

    @Override
    public boolean apply(final ComponentNode componentNode) {
        checkNotNull(componentNode);

        final Optional<T> optional = componentNode.get(propertyName);

        final boolean result;

        if (optional.isPresent()) {
            result = optional.get().equals(propertyValue);

            LOG.debug("apply() property name = {}, value = {}, result = {} for component node = {}",
                new Object[]{ propertyName, propertyValue, result, componentNode });
        } else {
            LOG.debug("apply() property name = {}, does not exist for component node = {}", propertyName,
                componentNode);

            result = false;
        }

        return result;
    }
}