/*
 * Copyright (C) 2014 JPEXS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.action.parser.script;

import com.jpexs.decompiler.flash.SourceGeneratorLocalData;
import com.jpexs.decompiler.flash.action.model.ActionItem;
import com.jpexs.decompiler.flash.helpers.GraphTextWriter;
import com.jpexs.decompiler.graph.GraphSourceItem;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.SourceGenerator;
import com.jpexs.decompiler.graph.model.LocalData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JPEXS
 */
public class VariableActionItem extends ActionItem {

    private ActionItem it;

    private final String variableName;
    private GraphTargetItem storeValue;
    private final boolean definition;

    public void setStoreValue(GraphTargetItem storeValue) {
        this.storeValue = storeValue;
    }

    public String getVariableName() {
        return variableName;
    }

    public VariableActionItem(String variableName, GraphTargetItem storeValue, boolean definition) {
        this.variableName = variableName;
        this.storeValue = storeValue;
        this.definition = definition;
    }

    public boolean isDefinition() {
        return definition;
    }

    public void setBoxedValue(ActionItem it) {
        this.it = it;
    }

    public ActionItem getBoxedValue() {
        return it;
    }

    public GraphTargetItem getStoreValue() {
        return storeValue;
    }

    @Override
    public GraphTextWriter appendTo(GraphTextWriter writer, LocalData localData) throws InterruptedException {
        if (it == null) {
            return writer;
        }
        return it.appendTo(writer, localData);
    }

    @Override
    public List<GraphSourceItem> toSource(SourceGeneratorLocalData localData, SourceGenerator generator) {
        if (it == null) {
            return new ArrayList<>();
        }
        return it.toSource(localData, generator);
    }

    @Override
    public List<GraphSourceItem> toSourceIgnoreReturnValue(SourceGeneratorLocalData localData, SourceGenerator generator) {
        if (it == null) {
            return new ArrayList<>();
        }
        return it.toSourceIgnoreReturnValue(localData, generator);
    }

    @Override
    public boolean hasReturnValue() {
        if (it == null) {
            return false;
        } else {
            return it.hasReturnValue();
        }
    }

    @Override
    public boolean needsSemicolon() {
        if (it == null) {
            return super.needsSemicolon();
        }
        return it.needsSemicolon();
    }

}