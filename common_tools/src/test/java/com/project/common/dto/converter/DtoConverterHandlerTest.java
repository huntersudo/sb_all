package com.project.common.dto.converter;

import com.project.common.constant.StatusCode;
import com.project.common.exception.RootException;
import com.project.common.util.MessageHelper;
import com.project.common.util.MockUtil;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * @author Wang Zheng
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DtoConverterHandler.class, MessageHelper.class})
@PowerMockIgnore("javax.management.*")
public class DtoConverterHandlerTest {

    @InjectMocks
    private DtoConverterHandler dch;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConvertSingle() throws Exception {
        final List<DtoConverter> converters = new ArrayList<>();
        // case 1
        final String code = "MSG999996";
        MockUtil.mockGetMessage(code);
        final Node node = new Node();
        node.setId(702);

        Whitebox.setInternalState(dch, "converterList", converters);

        // case 1
        try {
            dch.convertSingle(node);
        } catch (Exception e) {
            assertThat(code).isEqualTo(e.getMessage());
            Assertions.assertThat(StatusCode.SC_500.val()).isEqualTo(((RootException) e).getCode());
        }
        try {
            dch.convertSingle(null);
        } catch (Exception e) {
            assertThat(code).isEqualTo(e.getMessage());
            assertThat(StatusCode.SC_500.val()).isEqualTo(((RootException) e).getCode());
        }

        // case 2
        final NodeDto nodeDto = new NodeDto();
        nodeDto.setId(702);
        final DtoConverter dc = mock(DtoConverter.class);
        when(dc.support(Matchers.anyObject())).thenReturn(true, false);
        when(dc.convert(Matchers.anyObject())).thenReturn(nodeDto);
        converters.add(dc);

        final NodeDto result = (NodeDto) dch.convertSingle(node);
        assertThat(702).isEqualTo(result.getId());

        try {
            dch.convertSingle(node);
        } catch (Exception e) {
            assertThat(code).isEqualTo(e.getMessage());
            assertThat(StatusCode.SC_500.val()).isEqualTo(((RootException) e).getCode());
        }
    }

    @Test
    public void testConvertList() throws Exception {
        final List<Node> nodes = new ArrayList<Node>() {{
            Node n = new Node();
            n.setId(1);
            this.add(n);

            n = new Node();
            n.setId(2);
            this.add(n);
        }};

        dch = spy(dch);
        doReturn(new NodeDto() {{
            this.setId(1);
        }}).when(dch).convertSingle(Matchers.anyObject());

        final List<NodeDto> nodeDtos = dch.convertList(nodes);

        assertThat(2).isEqualTo(nodeDtos.size());
        assertThat(1).isEqualTo(nodeDtos.get(0).getId());
        assertThat(1).isEqualTo(nodeDtos.get(1).getId());
    }

    private class Node {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(final int id) {
            this.id = id;
        }
    }


    private class NodeDto {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(final int id) {
            this.id = id;
        }
    }
}