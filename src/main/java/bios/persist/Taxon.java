/*
 * Copyright (c) 2007-2014 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.persist;
/**
 *
 */
public class Taxon
  {
  Integer id;
  Rank rank;
  Integer parentId;
  String  name;
  String  commonName;

  public Taxon(){}

  public Taxon( Integer id, Rank rank, Integer parentId, String name, String commonName )
    {
    this.id = id;
    this.rank = rank;
    this.parentId = parentId;
    this.name = name;
    this.commonName = commonName;
    }

  public Integer getId()
    {
    return id;
    }

  public void setId( Integer id )
    {
    this.id = id;
    }

  public Rank getRank()
    {
    return rank;
    }

  public void setRank( Rank rank )
    {
    this.rank = rank;
    }

  public Integer getParentId()
    {
    return parentId;
    }

  public void setParentId( Integer parentId )
    {
    this.parentId = parentId;
    }

  public String getName()
    {
    return name;
    }

  public void setName( String name )
    {
    this.name = name;
    }

  public String getCommonName()
    {
    return commonName;
    }

  public void setCommonName( String commonName )
    {
    this.commonName = commonName;
    }
  }
