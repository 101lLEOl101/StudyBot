import {IconCheck, IconTrash} from '@tabler/icons-react';
import {ActionIcon, Box, Group, Paper, Table, Text} from '@mantine/core';
import {Link, useParams} from "react-router-dom";
import {IoCloseCircleOutline} from "react-icons/io5";

const data_students = [
    {
        tag: '@rifa',
        name: 'Robert Wolfkisser',
    },
    {
        tag: '@sdgdsg',
        name: 'Jill Jailbreaker',
    },
    {
        tag: '@jghjfnm',
        name: 'Henry Silkeater',
    },
    {
        tag: '@zxcz',
        name: 'Bill Horsefighter',
    },
    {
        tag: '@dsfbfb',
        name: 'Jeremy Footviewer',
    },
];

export default function RequestsStudentsComponent() {
    const rows = data_students.map((item) => (
        <Table.Tr key={item.name}>
            <Table.Td>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.name}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.tag}
                    </Text>
                </Group>
            </Table.Td>
            <Table.Td>
                <Group gap={0} justify="flex-end">
                    <ActionIcon variant="subtle" color="green">
                        <IconCheck size={16} stroke={1.5} />
                    </ActionIcon>
                    <ActionIcon variant="subtle" color="red">
                        <IconTrash size={16} stroke={1.5} />
                    </ActionIcon>
                </Group>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder >
            <Box display={"flex"} ml={"100%"}>
                <Link to={`/group-students/${useParams().id}`}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Table.ScrollContainer minWidth={800}>
                <Table verticalSpacing="sm">
                    <Table.Thead>
                        <Table.Tr>
                            <Table.Th>ФИО</Table.Th>
                            <Table.Th>Телеграм teg</Table.Th>
                        </Table.Tr>
                    </Table.Thead>
                    <Table.Tbody>{rows}</Table.Tbody>
                </Table>
            </Table.ScrollContainer>
        </Paper>
    );
}