/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2020 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.modules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.modules.util.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

/**
 * [MODULES-377] Getting 'IAE: moduleLoader is null' when iterating modules and module.xml contains a permissions markup
 *
 * @author <a href="mailto:ropalka@redhat.com">Richard Opalka</a>
 */
public class MODULES_377_Test extends AbstractModuleTestCase {
    private LocalModuleFinder moduleFinder;
    private ModuleLoader moduleLoader;

    @Before
    public void setup() throws Exception {
        final File repoRoot = Util.getResourceFile(getClass(), "test/MODULES_377");
        moduleFinder = new LocalModuleFinder(new File[] {repoRoot});
        moduleLoader = new ModuleLoader(moduleFinder);
    }

    @Test
    public void issueTest() {
        Iterator<String> i = moduleLoader.iterateModules((String) null, true);
        assertTrue(i.hasNext());
        assertEquals("local.tests.module", i.next());
        assertFalse(i.hasNext());
    }

}
