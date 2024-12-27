import {ActionIcon, Group, Paper, Table, Text} from '@mantine/core';
import {IconCheck} from "@tabler/icons-react";
import {IoCloseCircleOutline} from "react-icons/io5";

const data_students = [
    {
        tag: '@rifa',
        name: 'Robert Wolfkisser',
        correct: '10',
        incorrect: '10',
    },
    {
        tag: '@sdgdsg',
        name: 'Jill Jailbreaker',
        correct: '15',
        incorrect: '5',
    },
    {
        tag: '@jghjfnm',
        name: 'Henry Silkeater',
        correct: '5',
        incorrect: '15',
    },
    {
        tag: '@zxcz',
        name: 'Bill Horsefighter',
        correct: '12',
        incorrect: '8',
    },
    {
        tag: '@dsfbfb',
        name: 'Jeremy Footviewer',
        correct: '8',
        incorrect: '12',
    },
];


export default function TestComponent() {
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
                    <Text>{item.correct}</Text>
                    <ActionIcon variant="subtle" color="green">
                        <IconCheck size={16} stroke={1.5} />
                    </ActionIcon>
                    <Text>{item.incorrect}</Text>
                    <ActionIcon variant="subtle" color="red">
                        <IoCloseCircleOutline size={16} />
                    </ActionIcon>
                </Group>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <>
            <Text size="lg" c={"blue"} ta={"center"} p={10}>Сведения о Тесте</Text>
            <Paper maw={800} withBorder ml={"auto"} mr={"auto"} mb={50} p={10}>
                <Group>
                    <Paper ml={"auto"} mr={"auto"}>
                        <Text>Количество участников: 10</Text>
                        <Text>Процент правильных ответов: 80%</Text>
                        <Text>Начало теста: 27-12-25T20:00</Text>
                    </Paper>
                    <Paper ml={"auto"} mr={"auto"}>
                        <Text>Количество непрошедших: 5</Text>
                        <Text>Время до окончания: 2:00</Text>
                        <Text>Конец теста: 27-12-25T22:00</Text>
                    </Paper>
                </Group>
            </Paper>
            <Table.ScrollContainer minWidth={800}>
                <Text size="lg" c={"blue"} ta={"center"} p={10}>Результаты Студентов</Text>
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