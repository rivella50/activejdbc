/*
Copyright 2009-2010 Igor Polevoy 

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 

http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/


package activejdbc.cache;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * @author Igor Polevoy
 */
public class OSCacheManager implements CacheManager{

    private GeneralCacheAdministrator administrator;

    public OSCacheManager(){
        administrator = new GeneralCacheAdministrator();
    }

    public Object getCache(String key) {
        try {
            return administrator.getFromCache(key);
        } catch (NeedsRefreshException nre) {
            administrator.cancelUpdate(key);
        }
        return null; 
    }

    public void addCache(String group, String key, Object cache) {
        try{
            administrator.putInCache(key, cache, new String[]{group});
        }
        catch(Exception ignore){}
    }

    public void flushAll() {
        administrator.flushAll();
    }

    public void flushGroupCache(String group) {
        administrator.flushGroup(group);
    }
}