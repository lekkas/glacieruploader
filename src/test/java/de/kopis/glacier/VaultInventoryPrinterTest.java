package de.kopis.glacier;

/*
 * #%L
 * uploader
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Carsten Ringe
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.amazonaws.util.json.JSONException;

public class VaultInventoryPrinterTest {

  @Test
  public void testPrintInventoryListing() throws JSONException, IOException {
    final String inventory = readFile("target/test-classes/inventorylisting.txt");
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    new VaultInventoryPrinter(inventory).printInventory(out);
    assertEquals("ARN:\t\t\tarn:aws:glacier:eu-west-1:968744042024:vaults/mytestbackup\n"
        + "Archive ID:\t\tthisisaverylongrandomstringthatworksasthearchiveid\n"
        + "CreationDate:\t2012-08-23T04:14:56Z\n" + "Description:\ta custom description for your archive\n"
        + "Size:\t\t\t117.74\n" + "SHA:\t\t\t123456789123456789123456789\n", out.toString());
  }

  private String readFile(final String filename) throws IOException {
    String contents = "";
    final BufferedReader in = new BufferedReader(new FileReader(filename));
    String line = null;
    while ((line = in.readLine()) != null) {
      contents += line;
    }
    in.close();
    return contents;
  }

}
