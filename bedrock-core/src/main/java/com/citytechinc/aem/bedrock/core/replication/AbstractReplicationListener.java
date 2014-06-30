package com.citytechinc.aem.bedrock.core.replication;

import com.day.cq.replication.ReplicationAction;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listeners extending this class need to add the following SCR annotations to register the listener.
 * <p/>
 * <pre>
 * {@literal @}Component(immediate = true, metatype = true, inherit = true)
 * {@literal @}Service
 * {@literal @}Property(name = EventConstants.EVENT_TOPIC, value = ReplicationAction.EVENT_TOPIC)
 * </pre>
 */
public abstract class AbstractReplicationListener implements EventHandler {

    private static final String TYPE_ACTIVATE = "ACTIVATE";

    private static final String TYPE_DEACTIVATE = "DEACTIVATE";

    private static final Logger LOG = LoggerFactory.getLogger(AbstractReplicationListener.class);

    @Override
    public final void handleEvent(final Event event) {
        final String type = (String) event.getProperty(ReplicationAction.PROPERTY_TYPE);

        final String[] paths = (String[]) event.getProperty(ReplicationAction.PROPERTY_PATHS);

        if (paths != null) {
            for (final String path : paths) {
                handleEvent(type, path);
            }
        } else {
            final String path = (String) event.getProperty(ReplicationAction.PROPERTY_PATH);

            if (path != null) {
                handleEvent(type, path);
            }
        }
    }

    private void handleEvent(final String type, final String path) {
        if (TYPE_ACTIVATE.equals(type)) {
            LOG.info("handleEvent() handling activate event for path = {}", path);

            handleActivate(path);
        } else if (TYPE_DEACTIVATE.equals(type)) {
            LOG.info("handleEvent() handling deactivate event for path = {}", path);

            handleDeactivate(path);
        } else {
            LOG.info("handleEvent() type = {} not handled for path = {}", type, path);
        }
    }

    /**
     * Handle activation event.
     *
     * @param path payload path
     */
    protected abstract void handleActivate(final String path);

    /**
     * Handle deactivation event.
     *
     * @param path payload path
     */
    protected abstract void handleDeactivate(final String path);
}
