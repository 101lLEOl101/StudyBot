import {Badge, Group, Table, Text } from '@mantine/core';
import {stringToColour} from "../stringToColour.ts";

const data = [
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

export default function TestsComponent() {
    const rows = data.map((item) => (
        <Table.Tr key={item.name}>
            <Table.Td ta={"left"}>
                <Group gap="sm">
                    <Text fz="sm" fw={500}>
                        {item.name}
                    </Text>
                </Group>
            </Table.Td>

            <Table.Td ta={"center"}>
                <Badge color={stringToColour(item.discipline)}>
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

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Thead>
                    <Table.Tr>
                        <Table.Th ta={"left"}>Название Теста</Table.Th>
                        <Table.Th ta={"center"}>Дисциплина</Table.Th>
                        <Table.Th ta={"right"}>Окончание</Table.Th>
                    </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}