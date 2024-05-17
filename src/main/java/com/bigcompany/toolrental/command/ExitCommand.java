package com.bigcompany.toolrental.command;

import com.bigcompany.toolrental.util.PrintUtil;
import lombok.Builder;

@Builder
public class ExitCommand extends BaseCommand {

    @Override
    public void execute() {
        PrintUtil.println("Exiting...");
        System.exit(0);
    }
}
