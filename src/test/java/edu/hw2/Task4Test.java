package edu.hw2;

import edu.hw2.Task4.Task4;
import edu.hw2.Task4.Task4.CallingInfo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static class Proxy {
        public static CallingInfo doCall() {
            return Task4.callingInfo();
        }

        public static CallingInfo doPrivateCall() {
            return privateCall();
        }

        public static CallingInfo doClassCall() {
            return InnerProxy.innerProxyCall();
        }
        private static CallingInfo privateCall() {
            return Task4.callingInfo();
        }

        private static class InnerProxy {
            public static CallingInfo innerProxyCall() {
                return Task4.callingInfo();
            }
        }
    }

    @Test
    void simpleCallTest() {
        CallingInfo info = Task4.callingInfo();

        assertThat(info.className())
            .isEqualTo(this.getClass().getName());

        assertThat(info.methodName())
            .isEqualTo("simpleCallTest");
    }

    @Test
    void proxyCallTest() {
        CallingInfo info = Proxy.doCall();

        assertThat(info.className())
            .isEqualTo(Proxy.class.getName());

        assertThat(info.methodName())
            .isEqualTo("doCall");
    }

    @Test
    void proxyPrivateCallTest() {
        CallingInfo info = Proxy.doPrivateCall();

        assertThat(info.className())
            .isEqualTo(Proxy.class.getName());

        assertThat(info.methodName())
            .isEqualTo("privateCall");
    }

    @Test
    void innerProxyCallTest() {
        CallingInfo info = Proxy.doClassCall();

        assertThat(info.className())
            .isEqualTo(Proxy.InnerProxy.class.getName());

        assertThat(info.methodName())
            .isEqualTo("innerProxyCall");
    }
}
