package org.d.xpathx.xml;

import org.d.xpathx.data.Configuration;

/**
 *
 * @author xh.d
 * @since 2018/7/23 10:13
 */
public abstract class BaseBuilder {
    protected Configuration configuration;

    public BaseBuilder() {
    }

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
