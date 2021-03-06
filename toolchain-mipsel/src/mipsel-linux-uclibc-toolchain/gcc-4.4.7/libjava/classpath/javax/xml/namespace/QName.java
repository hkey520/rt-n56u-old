/* QName.java - An XML qualified name.
   Copyright (C) 2004,2005,2006 Free Software Foundation, Inc.

This file is part of GNU Classpath.

GNU Classpath is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
GNU Classpath is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with GNU Classpath; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. */


package javax.xml.namespace;

import gnu.java.lang.CPStringBuilder;

import java.io.Serializable;

import javax.xml.XMLConstants;

/**
 * An XML
 * <a href='http://www.w3.org/TR/REC-xml-names/#ns-qualnames'>qualified name</a>.
 *
 * @author <a href='mailto:dog@gnu.org'>Chris Burdess</a>
 * @since 1.3
 */
public class QName implements Serializable
{
  private static final long serialVersionUID = 4418622981026545151L;

  private final String namespaceURI;
  private final String localPart;
  private final String prefix;
  private transient String qName;
  transient int hashCode = -1;

  public QName(String namespaceURI, String localPart)
  {
    this(namespaceURI, localPart, null);
  }

  public QName(String namespaceURI, String localPart, String prefix)
  {
    if (namespaceURI == null)
      namespaceURI = XMLConstants.NULL_NS_URI;
    if (localPart == null)
      throw new IllegalArgumentException();
    if (prefix == null)
      prefix = XMLConstants.DEFAULT_NS_PREFIX;
    else
      {
        if (XMLConstants.XML_NS_PREFIX.equals(prefix))
          namespaceURI = XMLConstants.XML_NS_URI;
        else if (XMLConstants.XMLNS_ATTRIBUTE.equals(prefix))
          namespaceURI = XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
      }
    this.namespaceURI = namespaceURI;
    this.localPart = localPart;
    this.prefix = prefix;
  }

  public QName(String localPart)
  {
    this(null, localPart, null);
  }

  public String getNamespaceURI()
  {
    return namespaceURI;
  }

  public String getLocalPart()
  {
    return localPart;
  }

  public String getPrefix()
  {
    return prefix;
  }

  public final boolean equals(Object obj)
  {
    if (obj instanceof QName)
      {
        QName qname = (QName) obj;
        return qname.getLocalPart().equals(localPart) &&
          qname.getNamespaceURI().equals(namespaceURI);
      }
    return false;
  }

  public final int hashCode()
  {
    if (hashCode == -1)
      hashCode = localPart.hashCode() ^ namespaceURI.hashCode();
    return hashCode;
  }

  public synchronized String toString()
  {
    if (qName == null)
      {
	CPStringBuilder buf = new CPStringBuilder();
	if (namespaceURI.length() > 0)
	  {
	    buf.append('{');
	    buf.append(namespaceURI);
	    buf.append('}');
	  }
	if (prefix.length() > 0)
	  {
	    buf.append(prefix);
	    buf.append(':');
	  }
	buf.append(localPart);
	qName = buf.toString();
      }
    return qName;
  }

  public static QName valueOf(String qNameAsString)
  {
    if (qNameAsString == null)
      throw new IllegalArgumentException("qNameAsString can't be null");
    
    String namespaceUri = "", prefix = null;
    int start = qNameAsString.indexOf('{');
    int end = qNameAsString.indexOf('}');
    if (start != -1)
      {
        if (end < start)
          throw new IllegalArgumentException(qNameAsString);
        namespaceUri = qNameAsString.substring(start + 1, end);
        qNameAsString = qNameAsString.substring(end + 1);
      }
    start = qNameAsString.indexOf(':');
    if (start != -1)
      {
        prefix = qNameAsString.substring(0, start);
        qNameAsString = qNameAsString.substring(start + 1);
      }
    return new QName(namespaceUri, qNameAsString, prefix);
  }
  
}
