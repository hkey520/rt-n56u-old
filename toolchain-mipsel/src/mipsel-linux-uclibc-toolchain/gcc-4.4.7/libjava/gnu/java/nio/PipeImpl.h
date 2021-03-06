
// DO NOT EDIT THIS FILE - it is machine generated -*- c++ -*-

#ifndef __gnu_java_nio_PipeImpl__
#define __gnu_java_nio_PipeImpl__

#pragma interface

#include <java/nio/channels/Pipe.h>
extern "Java"
{
  namespace gnu
  {
    namespace java
    {
      namespace nio
      {
          class PipeImpl;
          class PipeImpl$SinkChannelImpl;
          class PipeImpl$SourceChannelImpl;
      }
    }
  }
  namespace java
  {
    namespace nio
    {
      namespace channels
      {
          class Pipe$SinkChannel;
          class Pipe$SourceChannel;
        namespace spi
        {
            class SelectorProvider;
        }
      }
    }
  }
}

class gnu::java::nio::PipeImpl : public ::java::nio::channels::Pipe
{

public:
  PipeImpl(::java::nio::channels::spi::SelectorProvider *);
  virtual ::java::nio::channels::Pipe$SinkChannel * sink();
  virtual ::java::nio::channels::Pipe$SourceChannel * source();
private:
  ::gnu::java::nio::PipeImpl$SinkChannelImpl * __attribute__((aligned(__alignof__( ::java::nio::channels::Pipe)))) sink__;
  ::gnu::java::nio::PipeImpl$SourceChannelImpl * source__;
public:
  static ::java::lang::Class class$;
};

#endif // __gnu_java_nio_PipeImpl__
