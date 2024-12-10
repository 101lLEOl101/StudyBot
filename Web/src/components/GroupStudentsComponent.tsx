import {ActionIcon, Badge, Box, Group, Table, Text} from '@mantine/core';
import {useParams} from "react-router-dom";
import {IconTrash} from "@tabler/icons-react";

const data_tests = [
    {
        name: 'Operation plus',
        discipline: 'Math',
        time_end: 'september 9 20:00'
    },
    {
        name: 'Law of Gravity',
        discipline: 'Physics',
        time_end: 'september 9 20:00'
    },
    {
        name: 'functions',
        discipline: 'Programming',
        time_end: 'september 9 20:00'
    },
];

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

const jobColors: Record<string, string> = {
    math: 'blue',
    physics: 'cyan',
    programming: 'pink',
};

export default function GroupStudentsComponent() {
    const rows_tests = data_tests.map((item) => (
        <Table.Tr key={item.name}>
            <Table.Td ta={"left"}>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.name}
                    </Text>
                </Group>
            </Table.Td>

            <Table.Td ta={"center"}>
                <Badge color={jobColors[item.discipline.toLowerCase()]} variant="light">
                    {item.discipline}
                </Badge>
            </Table.Td>
            <Table.Td ta={"right"}>
                <Text size="sm">
                    {item.time_end}
                </Text>
            </Table.Td>
        </Table.Tr>
    ));
    const rows_students = data_students.map((item) => (
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
                    <ActionIcon variant="subtle" color="red">
                        <IconTrash size={16} stroke={1.5} />
                    </ActionIcon>
                </Group>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <>
            <Box>{useParams().id}</Box>
            <Text size="lg" c={"blue"} ta={"center"} p={10}>Активные Тесты группы</Text>
            <Table.ScrollContainer minWidth={800}>
                <Table verticalSpacing="sm">
                    <Table.Thead>
                        <Table.Tr>
                            <Table.Th ta={"left"}>Название Теста</Table.Th>
                            <Table.Th ta={"center"}>Дисциплина</Table.Th>
                            <Table.Th ta={"right"}>Окончание</Table.Th>
                        </Table.Tr>
                    </Table.Thead>
                    <Table.Tbody>{rows_tests}</Table.Tbody>
                </Table>
                <Text size="lg" c={"blue"} ta={"center"} p={10}>Студенты Группы</Text>
                <Table verticalSpacing="sm">
                    <Table.Thead>
                        <Table.Tr>
                            <Table.Th>ФИО</Table.Th>
                            <Table.Th>Телеграм teg</Table.Th>
                        </Table.Tr>
                    </Table.Thead>
                    <Table.Tbody>{rows_students}</Table.Tbody>
                </Table>
            </Table.ScrollContainer>
        </>
    );
}