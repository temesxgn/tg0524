package com.bigcompany.toolrental.command;

import com.bigcompany.toolrental.service.ToolService;
import com.bigcompany.toolrental.util.PrintUtil;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Builder
@RequiredArgsConstructor
public class ListItemsCommand extends BaseCommand {

    private final ToolService toolService;

    @Override
    public void execute() {
        PrintUtil.println("Select item to purchase");
        PrintUtil.print("Item ID: ");
        String toolCode = scanner.next();
        System.out.println(toolService.findByCode(toolCode));

    }
}
