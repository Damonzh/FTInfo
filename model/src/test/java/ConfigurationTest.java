import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import io.github.damonzh.ftinfo.model.IConfigurationModel;
import io.github.damonzh.ftinfo.model.impl.ConfigurationModelImpl;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public class ConfigurationTest {

    private IConfigurationModel model;

    @Before
    public void setUp () {

        MockitoAnnotations.initMocks(this);

        model = new ConfigurationModelImpl();
    }

    @Test
    public void testConfigurationRequestExecution () {
        model.getConfiguration();
    }


}

