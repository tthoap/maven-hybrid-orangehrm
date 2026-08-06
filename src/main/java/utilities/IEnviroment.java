package utilities;

import org.aeonbits.owner.Config;

@Config.Sources({"file:enviromentConfig/env-${enviroment}.properties"})
public interface IEnviroment extends Config {
    @Config.Key("App.Url")
    String appUrl();

    @Config.Key("App.User")
    String appUser();

    @Config.Key("App.Pass")
    String appPass();

}
