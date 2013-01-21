package org.jboss.forge.tomee;

import org.jboss.forge.project.facets.events.InstallFacets;
import org.jboss.forge.project.facets.events.RemoveFacets;
import org.jboss.forge.shell.Shell;
import org.jboss.forge.shell.plugins.Alias;
import org.jboss.forge.shell.plugins.Command;
import org.jboss.forge.shell.plugins.Help;
import org.jboss.forge.shell.plugins.Plugin;
import org.jboss.forge.shell.plugins.SetupCommand;

import javax.enterprise.event.Event;
import javax.inject.Inject;

@Alias("tomee")
@Help("A plugin for tomee-maven-plugin usage")
public class TomEEPlugin implements Plugin {
    @Inject
    private Shell shell;

    @Inject
    private Event<InstallFacets> install;

    @Inject
    private Event<RemoveFacets> remove;

    @SetupCommand
    public void setup() {
        install.fire(new InstallFacets(TomEEFacet.class));
    }

    @Command("uninstall")
    public void uninstall() throws Exception {
        remove.fire(new RemoveFacets(TomEEFacet.class));
    }

    @Command("run")
    public void run() throws Exception {
        command("run");
    }

    @Command("debug")
    public void debug() throws Exception {
        command("debug");
    }

    @Command("start")
    public void start() throws Exception {
        command("start");
    }

    @Command("stop")
    public void stop() throws Exception {
        command("stop");
    }

    private void command(final String cmd) throws Exception {
        shell.execute("mvn tomee:" + cmd);
    }
}
