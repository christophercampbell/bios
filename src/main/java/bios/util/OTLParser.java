/*
 * Copyright (c) 2007-2009 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package bios.util;

import java.io.File;

/**
 * Open Tree of Life data parser, newick standard
 *
 * http://files.opentreeoflife.org/trees/
 */
public class OTLParser
  {
  final File data;
  final File output;

  public OTLParser( final File data, final File output )
    {
    this.data = data;
    this.output = output;
    }


  /**
   * Developed from http://yanwong.me/?page_id=1090
   *
   * First find the _ott number for the taxon you want (e.g. Arthropoda_ott632179)
   *
   */
  public void extractSubTree()
    {

    }




  }
