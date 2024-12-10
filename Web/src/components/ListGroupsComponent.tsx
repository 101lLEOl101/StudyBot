import {Button, Table, Text} from '@mantine/core';
import {Link} from "react-router-dom";

const data = [
    {
        name: 'Б23-534',
        id: 0,
        count_students: 20
    },
    {
        name: 'Б22-504',
        id: 1,
        count_students: 18
    },
    {
        name: 'С20-514',
        id: 2,
        count_students: 23
    },
];

export default function ListGroupsComponent() {
    const rows = data.map((item) => (
        <Table.Tr key={item.name}>
            <Table.Td ta={"left"}>
                <Link to={`/group-students/${item.id}`}>
                    <Button variant={"default"}>
                        <Text fz="sm" fw={500}>
                            {item.name}
                        </Text>
                    </Button>
                </Link>
            </Table.Td>
            <Table.Td ta={"right"}>
                <Text size="sm">
                    {item.count_students}
                </Text>
            </Table.Td>
        </Table.Tr>
    ));

    return (
        <Table.ScrollContainer minWidth={800}>
            <Table verticalSpacing="sm">
                <Table.Thead>
                    <Table.Tr>
                        <Table.Th ta={"left"}>Название Группы</Table.Th>
                        <Table.Th ta={"right"}>Количество учеников</Table.Th>
                    </Table.Tr>
                </Table.Thead>
                <Table.Tbody>{rows}</Table.Tbody>
            </Table>
        </Table.ScrollContainer>
    );
}